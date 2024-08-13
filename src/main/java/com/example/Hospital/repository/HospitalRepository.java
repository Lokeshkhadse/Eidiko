package com.example.Hospital.repository;

import com.example.Hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital , Long> {
}
