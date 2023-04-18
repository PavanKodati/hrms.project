package com.example.demo.major.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.major.project.DTO.UserDTO;
import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.security.SigninRequest;
import com.example.demo.major.project.service.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	@GetMapping("/hello")
	public String hello() {
		return "user";
	}

	@PostMapping(path = RestApiConfig.SIGNUP)
	public User saveuser(@Validated @RequestBody UserDTO us) {

		return authenticationService.saveuser(us);
	}

	@RequestMapping(path = RestApiConfig.SIGNIN, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody SigninRequest signinRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.OK).body(authenticationService.getLogIn(signinRequest));
	}
}
