package org.uncertweb.imagestorage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileImageStorage implements ImageStorage {

	private String basePath;
	private String baseURL;
	private SecureRandom random = new SecureRandom();
	
	private static final Logger logger = LoggerFactory.getLogger(FileImageStorage.class);

	public FileImageStorage(String basePath, String baseURL) {
		this.basePath = basePath;
		this.baseURL = baseURL;
	}

	@Override
	public BufferedImage retrieveImage(String id) throws ImageStorageException {
		try {
			File file = new File(basePath + System.getProperty("file.separator") + id + ".png");
			return ImageIO.read(file);
		}
		catch (IOException e) {
			throw new ImageStorageException("Couldn't read image from file.", e);
		}
	}

	@Override
	public String storeImage(BufferedImage image) throws ImageStorageException {
		try {
			String id = new BigInteger(130, random).toString(32);
			File dataDir = new File(basePath + System.getProperty("file.separator"));		
			if (!dataDir.exists()) {
				dataDir.mkdir();
			}
			File file = new File(dataDir, id + ".png");
			logger.info("Writing image to " + file.getPath());

			ImageIO.write(image, "png", file);
			return baseURL + (!baseURL.endsWith("/") ? "/" : "") + id;
		}
		catch (IOException e) {
			throw new ImageStorageException("Couldn't write image to file.", e);
		}
	}

}
