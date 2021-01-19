package com.example.api.resources.exception;

import java.time.Instant;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> objectNotFound() {
		StandardError erro = new StandardError(404, "Recurso não encontrado.", Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<StandardError> objectBad() {
		StandardError erro = new StandardError(400, "Bad Request.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> methodBad() {
		StandardError erro = new StandardError(405, "Método não permitido para este endpoint.", Instant.now());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> argumentIllegal() {
		StandardError erro = new StandardError(400, "Valor informado é inválida.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolation() {
		StandardError erro = new StandardError(400, "Recurso já cadastrado.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> ArgumentNotValid() {
		StandardError erro = new StandardError(400, "Informação inválida ou ausente.", Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	
	
	
		
}
