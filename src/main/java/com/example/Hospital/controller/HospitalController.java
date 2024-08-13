package com.example.Hospital.controller;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.entity.FileEntity;
import com.example.Hospital.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
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

    //for upload a file
    @PostMapping("/upload/{recordId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable Long recordId) throws Exception {
        hospitalService.saveFile(file, recordId);
        return ResponseEntity.ok("File uploaded successfully!");
    }

    // api for downloading file
    @GetMapping("/file/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileEntity fileEntity = hospitalService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFilename() + "\"")
                .body(fileEntity.getData());
    }

    //convert data into pdf and download
    @GetMapping("/download/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() {
        ByteArrayInputStream pdfStream = hospitalService.generatePdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
 //convert data into EXCEL and download
    @GetMapping("/download/excel")
    public ResponseEntity<InputStreamResource> downloadExcel() {
        ByteArrayInputStream in = hospitalService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }
}
