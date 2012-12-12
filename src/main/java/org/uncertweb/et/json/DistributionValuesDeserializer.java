package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.DistributionValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DistributionValuesDeserializer implements JsonDeserializer<DistributionValues> {

	@Override
	public DistributionValues deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		DistributionValues values = new DistributionValues();
		JsonArray array = json.getAsJsonArray();
		for (JsonElement element : array) {
			JsonObject object = element.getAsJsonObject();
			double mean = object.get("mean").getAsDouble();
			double variance = object.get("variance").getAsDouble();
			values.add(mean, variance);
		}
		return values;
	}
	
}
