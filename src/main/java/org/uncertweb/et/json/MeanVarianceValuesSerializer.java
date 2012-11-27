package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.MeanVariance;
import org.uncertweb.et.value.MeanVarianceValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MeanVarianceValuesSerializer implements JsonSerializer<MeanVarianceValues> {

	@Override
	public JsonElement serialize(MeanVarianceValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonArray array = new JsonArray();
		for (MeanVariance mv : src) {
			JsonObject obj = new JsonObject();
			obj.addProperty("mean", mv.getMean());
			obj.addProperty("variance", mv.getVariance());
			array.add(obj);
		}
		return array;
	}	
	
}
