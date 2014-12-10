package org.uncertweb.et.json;

import java.lang.reflect.Type;
import java.util.Map;

import org.uncertweb.et.quality.QualityIndicatorsResult;
import org.uncertweb.matlab.value.MLStruct;
import org.uncertweb.matlab.value.MLValue;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonParseException;

public class QualityIndicatorsResultSerializer implements JsonSerializer<QualityIndicatorsResult> {

	public JsonElement serialize(QualityIndicatorsResult src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();

		obj.add("struct", serializeMLValue(src.getQi(), context));

		return obj;
	}

	private JsonElement serializeMLValue(MLValue src, JsonSerializationContext context) {
		if (src.isScalar()) {
			return context.serialize(src.getAsScalar().getScalar());
		}
		else if (src.isArray()) {
			return context.serialize(src.getAsArray().getArray());
		}
		else if (src.isMatrix()) {
			return context.serialize(src.getAsMatrix().getMatrix());
		}
		else if (src.isString()) {
			return context.serialize(src.getAsString().getString());
		}
		else if (src.isCell()) {
			JsonObject object = new JsonObject();
			object.add("cell", context.serialize(src.getAsCell().getCell()));
			return object;
		}
		else if (src.isStruct()) {
			JsonObject object = new JsonObject();
			MLStruct struct = src.getAsStruct();
			for (Map.Entry<String, MLValue> e : struct.getStruct().entrySet()) {
				if (e.getKey().compareTo("compute") != 0) {
					object.add(e.getKey(), this.serializeMLValue(e.getValue(), context));
				}
			}
			return object;
		}
		else {
			throw new JsonParseException("Unable to serialize MLValue of type " + src.getClass().getName());
		}
	}
}
