package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

 @Autowired
  private DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long doctor_id) {
        return doctorRepository.findById(doctor_id).orElseThrow(() -> new ResourceNotFoundException("doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long doctor_id, Doctor doctor) {
        if(doctorRepository.existsById(doctor_id)){
            doctor.setDoctor_id(doctor_id);
            return doctorRepository.save(doctor);
        }
        return null;
    }

    @Override
    public void deleteDoctor(Long doctor_id) {
        doctorRepository.deleteById(doctor_id);
    }
}
