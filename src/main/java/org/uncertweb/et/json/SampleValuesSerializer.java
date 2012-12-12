package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.Sample;
import org.uncertweb.et.value.SampleValues;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SampleValuesSerializer implements JsonSerializer<SampleValues> {

	@Override
	public JsonElement serialize(SampleValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonArray root = new JsonArray();
		for (Sample e : src) {
			JsonObject o = new JsonObject();
			o.add("members", context.serialize(e.getMembers()));
			root.add(o);
		}
		return root;
	}	
	
}
