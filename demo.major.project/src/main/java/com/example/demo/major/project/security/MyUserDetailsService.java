package com.example.demo.major.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.exception.ResourceNotFoundException;
import com.example.demo.major.project.repository.AuthenticationRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthenticationRepository authenticationRepository;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {

		User user = authenticationRepository.findByemail(email);

		if (user == null) {
			System.out.println("aa");
			throw new ResourceNotFoundException("user not found");
			
		}

		return new LogInPrincple(user);

	}
}

