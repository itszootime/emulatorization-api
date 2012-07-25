package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DesignDeserializer implements JsonDeserializer<Design> {
	public Design deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		// design is an object
		JsonObject obj = json.getAsJsonObject();
		
		// create design
		Design design = new Design(obj.get("size").getAsInt());
		
		// get map
		JsonArray array = obj.get("map").getAsJsonArray();
		
		// add design for each input
		for (JsonElement element : array) {
			JsonObject o = element.getAsJsonObject();
			String inputIdentifier = o.get("inputIdentifier").getAsString();
			
			// create new design object if required
			if (o.has("mean") && !(design instanceof NormalisedDesign)) {
				design = new NormalisedDesign(design.getSize());
			}
			
			// add to design
			if (design instanceof NormalisedDesign) {
				NormalisedDesign n = (NormalisedDesign)design;
				Double[] points = context.deserialize(o.get("points"), Double[].class);
				n.addPoints(inputIdentifier, points, o.get("mean").getAsDouble(), o.get("stdDev").getAsDouble());
			} else {					
				if (o.has("points")) {					
					Double[] points = context.deserialize(o.get("points"), Double[].class);
					design.addPoints(inputIdentifier, points);
				}
				else {
					Double point = o.get("point").getAsDouble();
					design.addPoint(inputIdentifier, point);
				}
			}
		}
		
		return design;
	}
}