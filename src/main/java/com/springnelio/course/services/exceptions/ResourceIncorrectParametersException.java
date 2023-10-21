package com.springnelio.course.services.exceptions;

public class ResourceIncorrectParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceIncorrectParametersException(Object id) {
		super("Paramêtro incorreto. Id: " + id);
	}
}
