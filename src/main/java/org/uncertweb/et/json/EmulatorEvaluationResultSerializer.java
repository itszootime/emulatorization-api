package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.emulator.EmulatorEvaluationResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EmulatorEvaluationResultSerializer implements JsonSerializer<EmulatorEvaluationResult> {
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
}