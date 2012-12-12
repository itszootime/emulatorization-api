package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.ScalarValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ScalarValuesDeserializer implements JsonDeserializer<ScalarValues> {

	@Override
	public ScalarValues deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		JsonArray array = json.getAsJsonArray();
		double[] values = new double[array.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = array.get(i).getAsDouble();
		}
		return ScalarValues.fromArray(values);
	}

}
