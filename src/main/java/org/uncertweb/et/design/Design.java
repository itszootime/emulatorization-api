package org.uncertweb.et.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Design {

	protected List<String> inputIdentifierList;
	protected Map<String, Point> map;
	protected int size;

	public Design(int size) {
		inputIdentifierList = new ArrayList<String>();
		map = new HashMap<String, Point>();
		this.size = size;
	}

	/**
	 * this list defines the order of returned matrices. if something is removed from here...
	 * 
	 * @return
	 */
	public List<String> getInputIdentifiers() {
		return inputIdentifierList;
	}
	
	public void addPoint(String inputIdentifier, Double point) {
		if (!inputIdentifierList.contains(inputIdentifier)) {
			inputIdentifierList.add(inputIdentifier);
		}
		 map.put(inputIdentifier, new SinglePoint(point));
	}

	public void addPoints(String inputIdentifier, Double[] points) {
		if (!inputIdentifierList.contains(inputIdentifier)) {
			inputIdentifierList.add(inputIdentifier);
		}
		 map.put(inputIdentifier, new MultiplePoint(points));
	}
	
	/**
	 * Will duplicate for single point values
	 * 
	 * @param inputIdentifier
	 * @return
	 */
	public Double[] getPoints(String inputIdentifier) {
		Point point = map.get(inputIdentifier);
		if (point.isSinglePoint()) {
			Double[] array = new Double[getSize()];
			for (int i = 0; i < array.length; i++) {
				array[i] = point.getAsSinglePoint().getPoint();
			}
			return array;
		}
		else {
			return point.getAsMultiplePoint().getPoints();
		}
	}

	public Double[][] getPoints() {
		Double[][] pointsMatrix = new Double[getSize()][];
		for (int i = 0; i < getSize(); i++) {
			pointsMatrix[i] = new Double[inputIdentifierList.size()];
			// get point for each input
			for (int j = 0; j < inputIdentifierList.size(); j++) {
				String inputIdentifier = inputIdentifierList.get(j);
				Double[] points = getPoints(inputIdentifier);
				pointsMatrix[i][j] = points[i];
			}
		}
		return pointsMatrix;
	}
	
	public boolean hasMultiplePoints(String inputIdentifier) {
		return map.get(inputIdentifier) instanceof MultiplePoint;
	}

	public int getSize() {
		return size;
	}
	
}
