package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProcessEvaluationResultSerializer implements JsonSerializer<ProcessEvaluationResult> {
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
}