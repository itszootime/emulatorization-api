package org.uncertweb.json;

import java.awt.image.BufferedImage;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map.Entry;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.parameter.ConstantInput;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.request.Request;
import org.uncertweb.et.response.Response;
import org.uncertweb.et.sensitivity.AnalysisInputResult;
import org.uncertweb.imagestorage.Base64ImageStorage;
import org.uncertweb.imagestorage.ImageStorage;
import org.uncertweb.imagestorage.ImageStorageException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * By default, images will be encoded using {@link Base64ImageStorage}.
 * 
 * @author Richard Jones
 *
 */
public class JSON {

	private GsonBuilder gsonBuilder;
	private Gson gson;
	private ImageStorage is;

	public JSON() {
		this(new Base64ImageStorage());
	}
	
	public JSON(ImageStorage is) {
		// setup gson builder
		gsonBuilder = new GsonBuilder();
		registerSerializers(gsonBuilder);
		registerDeserializers(gsonBuilder);
		registerInstanceCreators(gsonBuilder);
		
		// set image storage
		this.is = is;

		// create gson
		gson = gsonBuilder.create();
	}

	public <T> T parse(Reader reader, Class<T> classOfT) {
		// FIXME: this should throw parse exceptions
		return gson.fromJson(reader, classOfT);
	}

	public void encode(Object obj, Appendable appendable) {
		if (obj instanceof Exception) {
			Exception src = (Exception) obj;
			JsonObject object = new JsonObject();
			object.add("type", new JsonPrimitive("Exception"));
			object.add("source", new JsonPrimitive(src.getClass().getSimpleName()));
			if (src.getMessage() != null) {
				object.add("message", new JsonPrimitive(src.getMessage()));
			}
			gson.toJson(object, appendable);
		}
		else if (obj instanceof Response) {
			JsonObject object = new JsonObject();
			object.addProperty("type", obj.getClass().getSimpleName());
			for (Entry<String, JsonElement> property : gson.toJsonTree(obj).getAsJsonObject().entrySet()) {
				object.add(property.getKey(), property.getValue());
			}
			gson.toJson(object, appendable);
		}
		else {
			gson.toJson(obj, appendable);
		}
	}

	private void registerSerializers(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(BufferedImage.class, new JsonSerializer<BufferedImage>() {
			public JsonElement serialize(BufferedImage src, Type typeOfSrc, JsonSerializationContext context) {
				try {
					String stored = is.storeImage(src);
					return new JsonPrimitive(stored);
				}
				catch (ImageStorageException e) {
					// FIXME: need exception
				}
				return null;
			}			
		});
	}

	private void registerDeserializers(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(JsonElement.class, new JsonDeserializer<JsonElement>() {
			public JsonElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				return json;
			}
		});
		gsonBuilder.registerTypeAdapter(Input.class, new JsonDeserializer<Input>() {
			public Input deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				JsonObject object = json.getAsJsonObject();
				String identifier = object.get("identifier").getAsString();
				Input input;
				if (object.has("value")) {
					input = new ConstantInput(identifier, object.get("value").getAsDouble());
				}
				else if (object.has("range")) {
					JsonObject range = object.get("range").getAsJsonObject();
					input = new VariableInput(identifier, range.get("min").getAsDouble(), range.get("max").getAsDouble());
				}
				else {
					input = new Input(identifier);
				}
				return input;
			}
		});
		gsonBuilder.registerTypeAdapter(Request.class, new JsonDeserializer<Request>() {
			public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				// get type
				JsonObject obj = json.getAsJsonObject();
				String type = obj.get("type").getAsString();
				obj.remove("type");

				// get class and deserialize
				try {
					Class<?> classOf = Class.forName("org.uncertweb.et.request." + type);
					return context.deserialize(obj, classOf);
				}
				catch (ClassNotFoundException e) {
					throw new JsonParseException("Cannot parse request of type " + type + ".");
				}
			}			
		});
		gsonBuilder.registerTypeAdapter(Design.class, new JsonSerializer<Design>() {
			public JsonElement serialize(Design src, Type typeOfSrc, JsonSerializationContext context) {
				// design is an object
				JsonObject obj = new JsonObject();
				obj.addProperty("size", src.getSize());
				
				// create map
				JsonArray map = new JsonArray();
				obj.add("map", map);

				// add design for each input
				for (String inputIdentifier : src.getInputIdentifiers()) {
					JsonObject o = new JsonObject();
					map.add(o);
					o.addProperty("inputIdentifier", inputIdentifier);
					if (src.hasMultiplePoints(inputIdentifier)) {					
						o.add("points", context.serialize(src.getPoints(inputIdentifier)));
					}
					else {
						o.addProperty("point", src.getPoints(inputIdentifier)[0]);
					}
					
					// add normalisation params
					if (src instanceof NormalisedDesign) {
						NormalisedDesign n = (NormalisedDesign)src;
						o.addProperty("mean", n.getMean(inputIdentifier));
						o.addProperty("stdDev", n.getStdDev(inputIdentifier));
					}
				}

				return obj;
			}
		});
		gsonBuilder.registerTypeAdapter(Design.class, new JsonDeserializer<Design>() {
			public Design deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				// design is an object
				JsonObject obj = json.getAsJsonObject();
				
				// create design
				Design design = new Design(obj.get("size").getAsInt());
				
				// get map
				JsonArray array = obj.get("map").getAsJsonArray();
				
				// add design for each input
				for (JsonElement element : array) {
					JsonObject o = element.getAsJsonObject();
					String inputIdentifier = o.get("inputIdentifier").getAsString();
					
					// create new design object if required
					if (o.has("mean") && !(design instanceof NormalisedDesign)) {
						design = new NormalisedDesign(design.getSize());
					}
					
					// add to design
					if (design instanceof NormalisedDesign) {
						NormalisedDesign n = (NormalisedDesign)design;
						Double[] points = context.deserialize(o.get("points"), Double[].class);
						n.addPoints(inputIdentifier, points, o.get("mean").getAsDouble(), o.get("stdDev").getAsDouble());
					} else {					
						if (o.has("points")) {					
							Double[] points = context.deserialize(o.get("points"), Double[].class);
							design.addPoints(inputIdentifier, points);
						}
						else {
							Double point = o.get("point").getAsDouble();
							design.addPoint(inputIdentifier, point);
						}
					}
				}
				
				return design;
			}
		});		
		gsonBuilder.registerTypeAdapter(ProcessEvaluationResult.class, new JsonSerializer<ProcessEvaluationResult>() {
			public JsonElement serialize(ProcessEvaluationResult src, Type typeOfSrc, JsonSerializationContext context) {
				// result is an array
				JsonArray array = new JsonArray();

				// add result for each output
				for (String outputIdentifier : src.getOutputIdentifiers()) {
					JsonObject obj = new JsonObject();
					array.add(obj);
					obj.addProperty("outputIdentifier", outputIdentifier);
					obj.add("results", context.serialize(src.getResults(outputIdentifier)));
					
					// add normalisation params
					if (src instanceof NormalisedProcessEvaluationResult) {
						NormalisedProcessEvaluationResult n = (NormalisedProcessEvaluationResult)src;
						obj.addProperty("mean", n.getMean(outputIdentifier));
						obj.addProperty("stdDev", n.getStdDev(outputIdentifier));
					}
				}

				return array;
			}
		});
		gsonBuilder.registerTypeAdapter(ProcessEvaluationResult.class, new JsonDeserializer<ProcessEvaluationResult>() {
			public ProcessEvaluationResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				// result is an array
				ProcessEvaluationResult evaluationResult = new ProcessEvaluationResult();
				JsonArray array = json.getAsJsonArray();
				
				// add results for each output
				for (JsonElement element : array) {
					JsonObject obj = element.getAsJsonObject();
					String outputIdentifier = obj.get("outputIdentifier").getAsString();
					
					// create new result object if required
					if (obj.has("mean") && !(evaluationResult instanceof NormalisedProcessEvaluationResult)) {
						evaluationResult = new NormalisedProcessEvaluationResult();
					}
					
					// add to result
					if (evaluationResult instanceof NormalisedProcessEvaluationResult) {
						NormalisedProcessEvaluationResult n = (NormalisedProcessEvaluationResult)evaluationResult;
						Double[] points = context.deserialize(obj.get("results"), Double[].class);
						n.addResults(outputIdentifier, points, obj.get("mean").getAsDouble(), obj.get("stdDev").getAsDouble());
					} else {					
						Double[] results = context.deserialize(obj.get("results"), Double[].class);
						evaluationResult.addResults(outputIdentifier, results);
					}					
				}
				
				return evaluationResult;
			}
		});		
		gsonBuilder.registerTypeAdapter(EmulatorEvaluationResult.class, new JsonSerializer<EmulatorEvaluationResult>() {
			public JsonElement serialize(EmulatorEvaluationResult src, Type typeOfSrc, JsonSerializationContext context) {
				// result is an array
				JsonArray array = new JsonArray();

				// add result for each output
				for (String outputIdentifier : src.getOutputIdentifiers()) {
					JsonObject obj = new JsonObject();
					array.add(obj);
					obj.addProperty("outputIdentifier", outputIdentifier);
					obj.add("meanResults", context.serialize(src.getMeanResults(outputIdentifier)));
					obj.add("covarianceResults", context.serialize(src.getCovarianceResults(outputIdentifier)));
				}

				return array;
			}
		});
		gsonBuilder.registerTypeAdapter(EmulatorEvaluationResult.class, new JsonDeserializer<EmulatorEvaluationResult>() {
			public EmulatorEvaluationResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				// result is an array
				EmulatorEvaluationResult emulatorResult = new EmulatorEvaluationResult();
				JsonArray array = json.getAsJsonArray();
				
				// add results for each output
				for (JsonElement element : array) {
					JsonObject obj = element.getAsJsonObject();
					String outputIdentifier = obj.get("outputIdentifier").getAsString();
					Double[] meanResults = context.deserialize(obj.get("meanResults"), Double[].class);
					Double[] covarianceResults = context.deserialize(obj.get("covarianceResults"), Double[].class);
					emulatorResult.addResults(outputIdentifier, meanResults, covarianceResults);
				}
				
				return emulatorResult;
			}
		});	
		gsonBuilder.registerTypeAdapter(AnalysisInputResult.class, new JsonSerializer<AnalysisInputResult>() {
			@Override
			public JsonElement serialize(AnalysisInputResult src, Type typeOfSrc, JsonSerializationContext context) {
				return context.serialize(src, src.getClass());
			}			
		});
	}

	private void registerInstanceCreators(GsonBuilder gsonBuilder) {
		gsonBuilder.registerTypeAdapter(Output.class, new InstanceCreator<Output>() {
			public Output createInstance(Type type) {
				return new Output("");
			}
		});
		gsonBuilder.registerTypeAdapter(ParameterDescription.class, new InstanceCreator<ParameterDescription>() {
			public ParameterDescription createInstance(Type type) {
				return new ParameterDescription("", DataType.Numeric);
			}
		});
	}

}
