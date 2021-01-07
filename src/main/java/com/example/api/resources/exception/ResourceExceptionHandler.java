package com.example.api.resources.exception;

import java.time.Instant;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> objectNotFound() {
		StandardError error = new StandardError(404, "Recurso não encontrado.", Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<StandardError> objectBad() {
		StandardError error = new StandardError(400, "Bad Request.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> methodBad() {
		StandardError error = new StandardError(405, "Método não permitido para este endpoint.", Instant.now());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
	}
	
}
