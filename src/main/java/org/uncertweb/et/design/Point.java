package org.uncertweb.et.design;

public abstract class Point {

	public boolean isSinglePoint() {
		return this instanceof SinglePoint;
	}
	
	public boolean isMultiplePoint() {
		return this instanceof MultiplePoint;
	}
	
	public SinglePoint getAsSinglePoint() {
		return (SinglePoint)this;
	}
	
	public MultiplePoint getAsMultiplePoint() {
		return (MultiplePoint)this;
	}
	
}