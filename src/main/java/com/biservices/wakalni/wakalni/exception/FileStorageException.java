package com.biservices.wakalni.wakalni.exception;

import java.io.IOException;

public class FileStorageException extends Exception {
	
	public FileStorageException(String message) {
		super(message);
	}
	
	public FileStorageException(String message, IOException exception) {
		super(message, exception);
	}
}
