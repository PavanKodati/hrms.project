package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.healthcard.Entity.Component;

public interface ComponentRepository extends JpaRepository<Component, String>{

}
