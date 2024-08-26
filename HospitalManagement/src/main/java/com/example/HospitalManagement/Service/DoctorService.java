package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    Doctor getDoctorById(Long doctor_id);

    List<Doctor> getAllDoctors();

    Doctor updateDoctor (Long doctor_id, Doctor doctor);

    void deleteDoctor(Long doctor_id);

}
