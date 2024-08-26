package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.Entity.Doctor;

import com.example.HospitalManagement.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctorDto) {
        Doctor createdDoctor = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/{doctor_id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctor_id") Long doctor_id) {
        Doctor doctorDto = doctorService.getDoctorById(doctor_id);
        return ResponseEntity.ok(doctorDto);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{doctor_id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctor_id") Long doctor_id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(doctor_id, doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{doctor_id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctor_id") Long doctor_id) {
        doctorService.deleteDoctor(doctor_id);
        return ResponseEntity.noContent().build();
    }
}
