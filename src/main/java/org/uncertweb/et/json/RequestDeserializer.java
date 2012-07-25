package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.request.Request;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class RequestDeserializer implements JsonDeserializer<Request> {
	public Request deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		// get type
		JsonObject obj = json.getAsJsonObject();
		String type = obj.get("type").getAsString();
		obj.remove("type");

		// get class and deserialize
		try {
			Class<?> classOf = Class.forName("org.uncertweb.et.request." + type);
			return context.deserialize(obj, classOf);
		}
		catch (ClassNotFoundException e) {
			throw new JsonParseException("Cannot parse request of type " + type + ".");
		}
	}
}