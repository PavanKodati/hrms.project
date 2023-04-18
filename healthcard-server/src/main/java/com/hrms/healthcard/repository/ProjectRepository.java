package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.healthcard.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

}
