package com.example.api.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.api.services.exception.DataIntegrityException;
import com.example.api.services.exception.ObjectNotFoundException;


@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
		StandardError erro = new StandardError(404, e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<StandardError> objectBad() {
		StandardError erro = new StandardError(400, "Valor informado é inválido.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> argumentIllegal() {
		StandardError erro = new StandardError(400, "Valor informado é inválido.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> methodBad() {
		StandardError erro = new StandardError(405, "Método não permitido para este endpoint.", Instant.now());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e) {
		StandardError erro = new StandardError(400, e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
		
}
