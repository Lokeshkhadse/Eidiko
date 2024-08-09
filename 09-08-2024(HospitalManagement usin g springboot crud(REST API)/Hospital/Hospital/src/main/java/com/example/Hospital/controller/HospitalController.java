package com.example.Hospital.controller;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController         // THIS CLASS HANDLE HTTP REQUEST
@RequestMapping("/api/patient")      // the base url for the all apis
public class HospitalController {

     private HospitalService hospitalService;

     //build api for create patient
   @PostMapping    // TO MAP INCOOMING http post request to this method
        public ResponseEntity<HospitalDto> createPatient(@RequestBody HospitalDto hospitalDto){
            HospitalDto savedPatient = hospitalService.createPatientdet(hospitalDto);
            return new ResponseEntity<>(savedPatient , HttpStatus.CREATED);
        }

        //build api for get patient
    @GetMapping("{patientid}")
        public ResponseEntity<HospitalDto> getPatientById(@PathVariable("patientid") Long patientId){
       HospitalDto hospitalDto = hospitalService.getPatientById(patientId);
       return  ResponseEntity.ok(hospitalDto);
        }


        //build get all employees rest api
    @GetMapping
    public ResponseEntity<List<HospitalDto>> getAllPatients(){
       List<HospitalDto> patients  = hospitalService.getAllPatients();
       return ResponseEntity.ok(patients);
    }

    //build UPDATE patient details rest api
   @PutMapping("{patientid}")
    public ResponseEntity<HospitalDto> updatePatient(@PathVariable("patientid") Long patientId,
                                                     @RequestBody HospitalDto updatedPatient){
       HospitalDto hospitalDto = hospitalService.updatePatient(patientId,updatedPatient);
       return   ResponseEntity.ok(hospitalDto);
    }

    //build delete  patient using rest api
  @DeleteMapping("{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable("patientId") Long patientId){
       hospitalService.deletePatient(patientId);
       return ResponseEntity.ok("Patient delete successfully");

    }

}
