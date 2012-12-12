package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.Distribution;
import org.uncertweb.et.value.DistributionValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DistributionValuesSerializer implements JsonSerializer<DistributionValues> {

	@Override
	public JsonElement serialize(DistributionValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonArray array = new JsonArray();
		for (Distribution mv : src) {
			JsonObject obj = new JsonObject();
			obj.addProperty("mean", mv.getMean());
			obj.addProperty("variance", mv.getVariance());
			array.add(obj);
		}
		return array;
	}	
	
}
