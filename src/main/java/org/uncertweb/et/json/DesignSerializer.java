package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DesignSerializer implements JsonSerializer<Design> {
	public JsonElement serialize(Design src, Type typeOfSrc, JsonSerializationContext context) {
		// design is an object
		JsonObject obj = new JsonObject();
		obj.addProperty("size", src.getSize());
		
		// create map
		JsonArray map = new JsonArray();
		obj.add("map", map);

		// add design for each input
		for (String inputIdentifier : src.getInputIdentifiers()) {
			JsonObject o = new JsonObject();
			map.add(o);
			o.addProperty("inputIdentifier", inputIdentifier);
			if (src.hasMultiplePoints(inputIdentifier)) {					
				o.add("points", context.serialize(src.getPoints(inputIdentifier)));
			}
			else {
				o.addProperty("point", src.getPoints(inputIdentifier)[0]);
			}
			
			// add normalisation params
			if (src instanceof NormalisedDesign) {
				NormalisedDesign n = (NormalisedDesign)src;
				o.addProperty("mean", n.getMean(inputIdentifier));
				o.addProperty("stdDev", n.getStdDev(inputIdentifier));
			}
		}

		return obj;
	}
}