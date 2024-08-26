package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Entity.MedicalRecord;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.dto.MedicalRecordDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T06:23:41-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class MedicalRecordMapperImpl implements MedicalRecordMapper {

    @Override
    public MedicalRecordDto toDTO(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }

        MedicalRecordDto medicalRecordDto = new MedicalRecordDto();

        medicalRecordDto.setPatientId( medicalRecordPatientPatient_id( medicalRecord ) );
        medicalRecordDto.setDoctorId( medicalRecordDoctorDoctor_id( medicalRecord ) );
        medicalRecordDto.setMedicalrecord_id( medicalRecord.getMedicalrecord_id() );
        medicalRecordDto.setRecordDate( medicalRecord.getRecordDate() );
        medicalRecordDto.setDetails( medicalRecord.getDetails() );

        return medicalRecordDto;
    }

    @Override
    public MedicalRecord toEntity(MedicalRecordDto medicalRecordDto) {
        if ( medicalRecordDto == null ) {
            return null;
        }

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setPatient( medicalRecordDtoToPatient( medicalRecordDto ) );
        medicalRecord.setDoctor( medicalRecordDtoToDoctor( medicalRecordDto ) );
        medicalRecord.setMedicalrecord_id( medicalRecordDto.getMedicalrecord_id() );
        medicalRecord.setRecordDate( medicalRecordDto.getRecordDate() );
        medicalRecord.setDetails( medicalRecordDto.getDetails() );

        return medicalRecord;
    }

    @Override
    public void updateEntityFromDTO(MedicalRecordDto medicalRecordDto, MedicalRecord medicalRecord) {
        if ( medicalRecordDto == null ) {
            return;
        }

        if ( medicalRecord.getPatient() == null ) {
            medicalRecord.setPatient( new Patient() );
        }
        medicalRecordDtoToPatient1( medicalRecordDto, medicalRecord.getPatient() );
        if ( medicalRecord.getDoctor() == null ) {
            medicalRecord.setDoctor( new Doctor() );
        }
        medicalRecordDtoToDoctor1( medicalRecordDto, medicalRecord.getDoctor() );
        medicalRecord.setRecordDate( medicalRecordDto.getRecordDate() );
        medicalRecord.setDetails( medicalRecordDto.getDetails() );
    }

    private Long medicalRecordPatientPatient_id(MedicalRecord medicalRecord) {
        Patient patient = medicalRecord.getPatient();
        if ( patient == null ) {
            return null;
        }
        return patient.getPatient_id();
    }

    private Long medicalRecordDoctorDoctor_id(MedicalRecord medicalRecord) {
        Doctor doctor = medicalRecord.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        return doctor.getDoctor_id();
    }

    protected Patient medicalRecordDtoToPatient(MedicalRecordDto medicalRecordDto) {
        if ( medicalRecordDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setPatient_id( medicalRecordDto.getPatientId() );

        return patient;
    }

    protected Doctor medicalRecordDtoToDoctor(MedicalRecordDto medicalRecordDto) {
        if ( medicalRecordDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctor_id( medicalRecordDto.getDoctorId() );

        return doctor;
    }

    protected void medicalRecordDtoToPatient1(MedicalRecordDto medicalRecordDto, Patient mappingTarget) {
        if ( medicalRecordDto == null ) {
            return;
        }

        mappingTarget.setPatient_id( medicalRecordDto.getPatientId() );
    }

    protected void medicalRecordDtoToDoctor1(MedicalRecordDto medicalRecordDto, Doctor mappingTarget) {
        if ( medicalRecordDto == null ) {
            return;
        }

        mappingTarget.setDoctor_id( medicalRecordDto.getDoctorId() );
    }
}
