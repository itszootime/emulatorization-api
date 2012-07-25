package org.uncertweb.et.json;

import java.lang.reflect.Type;

import org.uncertweb.et.parameter.Output;

import com.google.gson.InstanceCreator;

public class OutputInstanceCreator implements InstanceCreator<Output> {
	public Output createInstance(Type type) {
		return new Output("");
	}
}