package com.hrms.healthcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.healthcard.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {

}
