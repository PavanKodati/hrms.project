package com.hrms.healthcard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {

	private String credentials;
	private String provider;
	
}
