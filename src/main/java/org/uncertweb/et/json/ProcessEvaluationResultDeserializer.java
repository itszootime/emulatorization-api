package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ProcessEvaluationResultDeserializer implements JsonDeserializer<ProcessEvaluationResult> {
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
}