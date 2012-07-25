package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.emulator.EmulatorEvaluationResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EmulatorEvaluationResultDeserializer implements JsonDeserializer<EmulatorEvaluationResult> {
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
}