package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.sensitivity.AnalysisInputResult;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AnalysisInputResultSerializer implements JsonSerializer<AnalysisInputResult> {
	@Override
	public JsonElement serialize(AnalysisInputResult src, Type typeOfSrc, JsonSerializationContext context) {
		return context.serialize(src, src.getClass());
	}
}