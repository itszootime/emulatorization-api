package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.Sample;
import org.uncertweb.et.value.SampleValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class SampleValuesDeserializer implements JsonDeserializer<SampleValues> {

	@Override
	public SampleValues deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		JsonArray array = json.getAsJsonArray();
		SampleValues values = new SampleValues();
		for (JsonElement element : array) {
			JsonArray membersArray = element.getAsJsonObject().get("members").getAsJsonArray();
			double[] members = new double[membersArray.size()];
			for (int i = 0; i < members.length; i++) {
				members[i] = membersArray.get(i).getAsDouble();
			}
			values.add(new Sample(members));
		}
		return values;
	}

	
	
}
