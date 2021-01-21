package com.example.api.resources.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String message, Instant data, List<FieldMessage> errors) {
		super(status, message, data);
		this.errors = errors;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	
}
