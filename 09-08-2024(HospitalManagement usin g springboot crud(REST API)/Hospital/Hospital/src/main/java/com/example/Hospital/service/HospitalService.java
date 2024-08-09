package com.example.Hospital.service;

import com.example.Hospital.dto.HospitalDto;

import java.util.List;

public interface HospitalService {
    HospitalDto createPatientdet(HospitalDto hospitalDto);

    HospitalDto getPatientById(Long patientId);

    List<HospitalDto> getAllPatients();

     HospitalDto updatePatient(Long patientId ,HospitalDto updatedPatient );

     void deletePatient(Long patientId);

}
