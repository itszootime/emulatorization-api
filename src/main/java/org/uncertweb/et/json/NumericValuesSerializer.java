package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.value.NumericValues;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class NumericValuesSerializer implements JsonSerializer<NumericValues> {

	@Override
	public JsonElement serialize(NumericValues src, Type typeOfSrc,
			JsonSerializationContext context) {
		return context.serialize(src.toArray());
	}	
	
}
