package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.Ensemble;
import org.uncertweb.et.value.EnsembleValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EnsembleValuesSerializer implements JsonSerializer<EnsembleValues> {

	@Override
	public JsonElement serialize(EnsembleValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonArray root = new JsonArray();
		for (Ensemble e : src) {
			JsonObject o = new JsonObject();
			o.add("members", context.serialize(e.getMembers()));
			root.add(o);
		}
		return root;
	}	
	
}
