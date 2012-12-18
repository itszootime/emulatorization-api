package org.uncertweb.et.json;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.response.Include;
import org.uncertweb.et.response.Respondable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RespondableSerializer implements JsonSerializer<Respondable> {
	
	private static final Logger logger = LoggerFactory.getLogger(RespondableSerializer.class);

	@Override
	public JsonElement serialize(Respondable src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.addProperty("type", src.getResponseName() + "Response");

		for (Field field : src.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if (field.getAnnotation(Include.class) != null) {					
					obj.add(field.getName(), context.serialize(field.get(src)));
				}
			}
			catch (IllegalAccessException e) {
				// can't do much here!
				logger.error("Unable to serialize field " + field.getName() + " in respondable class " + src.getClass().getName(), e);
			}
		}

		return obj;
	}	

}
