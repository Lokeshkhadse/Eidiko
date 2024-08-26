package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.dto.MedicalRecordDto;
import com.example.HospitalManagement.Service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        MedicalRecordDto createdRecord = medicalRecordService.createMedicalRecord(medicalRecordDto);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{medicalrecord_id}")
    public ResponseEntity<MedicalRecordDto> getMedicalRecordById(@PathVariable("medicalrecord_id") Long medicalrecord_id) {
        MedicalRecordDto medicalRecordDto = medicalRecordService.getMedicalRecordByRecordId(medicalrecord_id);
        return ResponseEntity.ok(medicalRecordDto);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordDto>> getAllMedicalRecords() {
        List<MedicalRecordDto> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicalRecords);
    }

    @PutMapping("/{medicalrecord_id}")
    public ResponseEntity<MedicalRecordDto> updateMedicalRecord(@PathVariable("medicalrecord_id") Long medicalrecord_id, @RequestBody MedicalRecordDto medicalRecordDto) {
        MedicalRecordDto updatedRecord = medicalRecordService.updateMedicalRecord(medicalrecord_id, medicalRecordDto);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{medicalrecord_id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable("medicalrecord_id") Long medicalrecord_id) {
        medicalRecordService.deleteRecord(medicalrecord_id);
        return ResponseEntity.noContent().build();
    }
}
