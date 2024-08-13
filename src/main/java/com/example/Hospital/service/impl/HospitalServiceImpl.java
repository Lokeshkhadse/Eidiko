package com.example.Hospital.service.impl;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.entity.FileEntity;
import com.example.Hospital.entity.Hospital;
import com.example.Hospital.exception.ResourceNotFoundException;
import com.example.Hospital.mapper.HospitalMapper;
import com.example.Hospital.repository.FileRepository;
import com.example.Hospital.repository.HospitalRepository;
import com.example.Hospital.service.HospitalService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
//import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @Autowired
    private FileRepository fileRepository;


    // upload file ch logic
    @Override
    public void saveFile(MultipartFile file, Long patientid) throws Exception {
        Hospital userId = hospitalRepository.findById(patientid)
                .orElseThrow(() -> new Exception("Record not found"));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setFiletype(getFileExtension(file.getOriginalFilename()));
        fileEntity.setData(file.getBytes());
        fileEntity.setUploaddate(LocalDateTime.now());
        fileEntity.setPatientid(userId);

        fileRepository.save(fileEntity);
    }
    //kuthli file ahe tyach extension get karun denar
    private String getFileExtension(String filename){
        return filename.substring(filename.lastIndexOf(".")+1);
    }
//   download file
    @Override
    public FileEntity getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    //CREATE PDF WHIT DETAILS
    @Override
    public ByteArrayInputStream generatePdf() {
        List<Hospital> users = hospitalRepository.findAll();

           Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph title = new Paragraph("User Details");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Adding a blank line

            PdfPTable table = new PdfPTable(7); // Number of columns
            table.setWidthPercentage(100);

            // Adding table headers
            addTableHeader(table, "Patient ID");
            addTableHeader(table, "Name");
            addTableHeader(table, "Gender");
            addTableHeader(table, "Contact Number");
            addTableHeader(table, "Dob");
            addTableHeader(table, "MedicalHistory");
            addTableHeader(table, "DocName");


            // Adding user details to the table
            for (Hospital user : users) {
                table.addCell(user.getPatientid() != null ? user.getPatientid().toString() : "N/A");
                table.addCell(user.getName() != null ? user.getName() : "N/A");
                table.addCell(user.getGender() != null ? user.getGender() : "N/A");
                table.addCell(user.getContactno() != null ? user.getContactno() : "N/A");
                table.addCell(user.getDob() != null ? user.getDob().toString() : "N/A");
                table.addCell(user.getMedicalhistory() != null ? user.getMedicalhistory(): "N/A");
                table.addCell(user.getDocName()!= null ? user.getDocName() : "N/A");
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    //for heading
    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Paragraph(headerTitle));
        table.addCell(header);
    }


// generte excel and download it
    @Override
    public ByteArrayInputStream generateExcel() {
        List<Hospital> users = hospitalRepository.findAll(); // Replace with your actual method to fetch users

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("User Details");

            // Creating the header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Patient ID", " Name", "Gender", "Contact Number", "dob", "MedicalHistory", "DocName"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Adding user data rows
            int rowIndex = 1;
            for (Hospital user : users) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getPatientid() != null ? user.getPatientid() : 0);
                row.createCell(1).setCellValue(user.getName() != null ? user.getName() : "N/A");
                row.createCell(2).setCellValue(user.getGender() != null ? user.getGender() : "N/A");
                row.createCell(3).setCellValue(user.getContactno() != null ? user.getContactno() : "N/A");
                row.createCell(4).setCellValue(user.getDob() != null ? user.getDob().toString() : "N/A");
                row.createCell(5).setCellValue(user.getMedicalhistory() != null ? user.getMedicalhistory() : "N/A");
                row.createCell(6).setCellValue(user.getDocName() != null ? user.getDocName().toString() : "N/A");
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerCellStyle.setFont(font);
        return headerCellStyle;
    }

}
