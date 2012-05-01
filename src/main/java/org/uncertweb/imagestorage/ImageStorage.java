package org.uncertweb.imagestorage;

import java.awt.image.BufferedImage;

public interface ImageStorage {
	
	public BufferedImage retrieveImage(String stored) throws ImageStorageException;	
	public String storeImage(BufferedImage image) throws ImageStorageException;
	
}
