package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.DistributionValues;
import org.uncertweb.et.value.SampleValues;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ValuesDeserializer implements JsonDeserializer<Values> {

	@Override
	public Values deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		// peek to decide what we're deserializing
		JsonArray array = json.getAsJsonArray();
		JsonElement first = array.get(0);
		if (first.isJsonObject()) {
			if (first.getAsJsonObject().has("mean")) {
				// distribution
				return context.deserialize(json, DistributionValues.class);
			}
			else {
				// samples
				return context.deserialize(json, SampleValues.class);
			}
		}
		else {
			// scalar
			return context.deserialize(json, ScalarValues.class);
		}
	}
	
}
