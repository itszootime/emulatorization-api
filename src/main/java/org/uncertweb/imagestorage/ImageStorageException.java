package org.uncertweb.imagestorage;

public class ImageStorageException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ImageStorageException() {
		super();
	}
	
	public ImageStorageException(String message) {
		super(message);
	}
	
	public ImageStorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
