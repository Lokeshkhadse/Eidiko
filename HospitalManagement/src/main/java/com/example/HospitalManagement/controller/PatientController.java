package com.example.HospitalManagement.controller;



import com.example.HospitalManagement.Entity.Patient;

import com.example.HospitalManagement.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping("/{patient_id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patient_id") Long patient_id) {
        Patient patientDto = patientService.getPatientById(patient_id);
        return ResponseEntity.ok(patientDto);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{patient_id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("patient_id") Long patient_id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(patient_id, patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patient_id") Long patient_id) {
        patientService.deletePatient(patient_id);
        return ResponseEntity.noContent().build();
    }
}
