package com.example.Hospital.service;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.util.List;

public interface HospitalService {
    HospitalDto createPatientdet(HospitalDto hospitalDto);

    HospitalDto getPatientById(Long patientId);

    List<HospitalDto> getAllPatients();

     HospitalDto updatePatient(Long patientId ,HospitalDto updatedPatient );

     void deletePatient(Long patientId);

     //for uplaod a file
    void saveFile(MultipartFile file , Long patientId) throws Exception;

    //download file
       FileEntity getFile(Long id);

       //download pdf file
       ByteArrayInputStream generatePdf();


       //generate excel and download it.

       ByteArrayInputStream generateExcel();

}
