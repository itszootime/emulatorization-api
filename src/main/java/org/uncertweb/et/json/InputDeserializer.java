package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.parameter.ConstantInput;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.VariableInput;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class InputDeserializer implements JsonDeserializer<Input> {
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
		
		// description
		if (object.has("description")) {
			ParameterDescription description = (ParameterDescription)context.deserialize(object.get("description"), ParameterDescription.class);
			input.setDescription(description);
		}
		
		return input;
	}
}