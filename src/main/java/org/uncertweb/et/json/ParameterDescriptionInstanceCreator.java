package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;

import com.google.gson.InstanceCreator;

public class ParameterDescriptionInstanceCreator implements InstanceCreator<ParameterDescription> {
	public ParameterDescription createInstance(Type type) {
		return new ParameterDescription("", DataType.Numeric);
	}
}