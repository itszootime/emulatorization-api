package org.uncertweb.imagestorage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class Base64ImageStorage implements ImageStorage {

	@Override
	public BufferedImage retrieveImage(String stored) throws ImageStorageException {
		try {
			byte[] bytes = Base64.decodeBase64(stored);
			return ImageIO.read(new ByteArrayInputStream(bytes));
		}
		catch (IOException e) {
			throw new ImageStorageException("Couldn't read image from base64 string.", e);
		}
	}

	@Override
	public String storeImage(BufferedImage image) throws ImageStorageException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, "png", out);
			return Base64.encodeBase64String(out.toByteArray());
		}
		catch (IOException e) {
			throw new ImageStorageException("Couldn't write image to base64 string.", e);
		}
	}

}
