package com.springnelio.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springnelio.course.services.exceptions.DbIntegrityException;
import com.springnelio.course.services.exceptions.ResourceIncorrectParametersException;
import com.springnelio.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Recurso não encontrado";
		StandartError erro = new StandartError(Instant.now(), status.toString(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(ResourceIncorrectParametersException.class)
	public ResponseEntity<StandartError> resourceIncorrectParameters(ResourceIncorrectParametersException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Parâmetro incorreto";
		StandartError erro = new StandartError(Instant.now(), status.toString(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(DbIntegrityException.class)
	public ResponseEntity<StandartError> dbIntegrityException(DbIntegrityException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de integridade";
		StandartError erro = new StandartError(Instant.now(), status.toString(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
}
