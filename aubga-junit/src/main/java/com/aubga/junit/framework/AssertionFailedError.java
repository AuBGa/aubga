package com.aubga.junit.framework;

public class AssertionFailedError extends AssertionError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6225685184086200506L;
	
	public AssertionFailedError() {
		
	}
	
	public AssertionFailedError(String message) {
		super(message);
	}
}

