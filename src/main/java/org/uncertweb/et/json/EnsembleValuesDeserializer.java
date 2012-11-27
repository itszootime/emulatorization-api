package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.Ensemble;
import org.uncertweb.et.value.EnsembleValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EnsembleValuesDeserializer implements JsonDeserializer<EnsembleValues> {

	@Override
	public EnsembleValues deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		JsonArray array = json.getAsJsonArray();
		EnsembleValues values = new EnsembleValues();
		for (JsonElement element : array) {
			JsonArray membersArray = element.getAsJsonObject().get("members").getAsJsonArray();
			double[] members = new double[membersArray.size()];
			for (int i = 0; i < members.length; i++) {
				members[i] = membersArray.get(i).getAsDouble();
			}
			values.add(new Ensemble(members));
		}
		return values;
	}

	
	
}
