package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.MeanVarianceValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MeanVarianceValuesDeserializer implements JsonDeserializer<MeanVarianceValues> {

	@Override
	public MeanVarianceValues deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		MeanVarianceValues values = new MeanVarianceValues();
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
