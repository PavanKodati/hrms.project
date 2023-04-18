package com.hrms.healthcard.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = null;
			String errorMessage = null;
			fieldName = ((FieldError) error).getField();
			errorMessage = error.getDefaultMessage();

			if (fieldName.equalsIgnoreCase("flid") || fieldName.equalsIgnoreCase("pin")) {
				fieldName = "message";
				errorMessage = "Please Enter Valid FlId or Password";
			}
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<?> handleAllException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(tokenExpirationException.class)
	public final ResponseEntity<ApiResponce> handletokenExpiredException(tokenExpirationException ex) {
		ApiResponce api = new ApiResponce(ex.getMessage(), "false");

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(api);
	}

}

//@ExceptionHandler(JwtCustomException.class)
//public final ResponseEntity<ApiResponce> handleException(JwtCustomException ex) {
//	ApiResponce api = new ApiResponce(ex.getMessage(), "false");
//
//	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(api);
//}
