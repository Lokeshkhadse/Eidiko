package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long patient_id) {
        return  patientRepository.findById(patient_id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Long patient_id, Patient patient) {
        if(patientRepository.existsById(patient_id)){
            patient.setPatient_id(patient_id);
            return patientRepository.save(patient);
        }
        return null;
    }

    @Override
    public void deletePatient(Long patient_id) {
        patientRepository.deleteById(patient_id);
    }

}
