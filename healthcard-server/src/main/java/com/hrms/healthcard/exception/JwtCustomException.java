package com.hrms.healthcard.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtCustomException extends RuntimeException {
	
	private String message;

	public JwtCustomException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

}
