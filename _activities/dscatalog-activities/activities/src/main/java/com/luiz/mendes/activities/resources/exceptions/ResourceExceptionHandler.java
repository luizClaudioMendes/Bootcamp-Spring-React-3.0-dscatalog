package com.luiz.mendes.activities.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luiz.mendes.activities.services.exceptions.DatabaseException;
import com.luiz.mendes.activities.services.exceptions.ResourceNotFoundException;

@ControllerAdvice 
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(
			ResourceNotFoundException error, 
			HttpServletRequest request
			) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(error.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> entityNotFound(
			DatabaseException error, 
			HttpServletRequest request
			) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(error.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}


}
