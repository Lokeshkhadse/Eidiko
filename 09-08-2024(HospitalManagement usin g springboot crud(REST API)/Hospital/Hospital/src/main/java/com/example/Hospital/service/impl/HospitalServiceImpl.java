package com.example.Hospital.service.impl;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.entity.Hospital;
import com.example.Hospital.exception.ResourceNotFoundException;
import com.example.Hospital.mapper.HospitalMapper;
import com.example.Hospital.repository.HospitalRepository;
import com.example.Hospital.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HospitalServiceImpl  implements HospitalService {

    private HospitalRepository hospitalRepository;

    @Override
    public HospitalDto createPatientdet(HospitalDto hospitalDto) {

        Hospital hospital = HospitalMapper.mapToHospital(hospitalDto);
        Hospital savedHospitalPatient = hospitalRepository.save(hospital);

        return HospitalMapper.mapToHospitalDto(savedHospitalPatient);
    }

    @Override
    public HospitalDto getPatientById(Long patientId) {
        Hospital hospital =hospitalRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PATIENT IS NOT EXIST OF THIS GIVEN ID" + patientId));

        return HospitalMapper.mapToHospitalDto(hospital);
    }

    @Override
    public List<HospitalDto> getAllPatients() {

        List<Hospital> patients = hospitalRepository.findAll();
        return patients.stream().map((hospital) -> HospitalMapper.mapToHospitalDto(hospital))
                .collect(Collectors.toList());
    }

    @Override
    public HospitalDto updatePatient(Long patientId, HospitalDto updatedPatient) {
       Hospital hospital=  hospitalRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("patient is not their : " +patientId)
        );
     hospital.setName(updatedPatient.getName());
     hospital.setDob(updatedPatient.getDob());
     hospital.setGender(updatedPatient.getGender());
     hospital.setContactno(updatedPatient.getContactno());
     hospital.setGender(updatedPatient.getGender());
     hospital.setMedicalhistory(updatedPatient.getMedicalhistory());
     hospital.setDocName(updatedPatient.getDocName());

   Hospital  updatedPatientobj =  hospitalRepository.save(hospital);     // save method perfor both insert and update operation
        return HospitalMapper.mapToHospitalDto(updatedPatientobj);
    }

    @Override
    public void deletePatient(Long patientId) {

        Hospital hospital =hospitalRepository.findById(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PATIENT IS NOT EXIST OF THIS GIVEN ID" + patientId));

        hospitalRepository.deleteById(patientId);
    }
}
