package com.example.demo.major.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.major.project.domain.Slot;
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

}
