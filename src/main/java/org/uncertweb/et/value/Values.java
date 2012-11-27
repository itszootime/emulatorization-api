package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Values<T extends Value> implements Iterable<T> {
	
	// each value can have an identifier?
	
	private List<T> list;
	
	protected Values() {
		list = new ArrayList<T>();
	}
	
	public void add(T value) {
		list.add(value);
	}
	
	public T get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public List<T> getValues() {
		return list;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
