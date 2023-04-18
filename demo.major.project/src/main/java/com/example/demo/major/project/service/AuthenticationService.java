package com.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.major.project.DTO.UserDTO;
import com.example.demo.major.project.domain.User;
import com.example.demo.major.project.repository.AuthenticationRepository;
import com.example.demo.major.project.repository.BookingRepository;
import com.example.demo.major.project.security.AdminLogIn;
import com.example.demo.major.project.security.JwtUtil;
import com.example.demo.major.project.security.LogInPrincple;
import com.example.demo.major.project.security.SigninRequest;
import com.example.demo.major.project.security.UserLogIn;

@Service
public class AuthenticationService {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public User saveuser(UserDTO userDTO) {
		User user=new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setCity(userDTO.getCity());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setBuildings(userDTO.getBuildings());
		return authenticationRepository.save(user);
	}

	public Object getLogIn(SigninRequest signinRequest) {

		Authentication auth = null;
		auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

		LogInPrincple princple = (LogInPrincple) auth.getPrincipal();

		User myuser = princple.getUser();
		Integer id = myuser.getId();
		String role = myuser.getRole();
		final String jwt = jwtUtil.generateToken(princple);
		if (role.equals("admin")) {
			User user = authenticationRepository.findById(id).orElse(null);
			return new AdminLogIn(jwt, user.getBuildings());
		} else {
			return new UserLogIn(jwt, bookingRepository.endUserResult(id));
		}
	}
}
