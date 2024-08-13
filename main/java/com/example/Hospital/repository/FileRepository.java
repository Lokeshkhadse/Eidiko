package com.example.Hospital.repository;

import com.example.Hospital.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity , Long> {
}
