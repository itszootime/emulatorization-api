package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.ScalarValues;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ScalarValuesSerializer implements JsonSerializer<ScalarValues> {

	@Override
	public JsonElement serialize(ScalarValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.toArray());
	}	
	
}
