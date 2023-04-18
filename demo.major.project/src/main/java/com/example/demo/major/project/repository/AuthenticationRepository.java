package com.example.demo.major.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.major.project.domain.User;

public interface AuthenticationRepository extends JpaRepository<User, Integer> {

	User findByemail(String email);


	
}
