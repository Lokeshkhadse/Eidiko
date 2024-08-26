package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Entity.Operation;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.dto.OperationDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T06:23:42-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class OperationMapperImpl implements OperationMapper {

    @Override
    public OperationDto toDTO(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationDto operationDto = new OperationDto();

        operationDto.setPatientId( operationPatientPatient_id( operation ) );
        operationDto.setDoctorId( operationDoctorDoctor_id( operation ) );
        operationDto.setOperation_id( operation.getOperation_id() );
        operationDto.setOperationDate( operation.getOperationDate() );
        operationDto.setDetails( operation.getDetails() );

        return operationDto;
    }

    @Override
    public Operation toEntity(OperationDto operationDto) {
        if ( operationDto == null ) {
            return null;
        }

        Operation operation = new Operation();

        operation.setPatient( operationDtoToPatient( operationDto ) );
        operation.setDoctor( operationDtoToDoctor( operationDto ) );
        operation.setOperation_id( operationDto.getOperation_id() );
        operation.setOperationDate( operationDto.getOperationDate() );
        operation.setDetails( operationDto.getDetails() );

        return operation;
    }

    @Override
    public void updateEntityFromDTO(OperationDto operationDto, Operation operation) {
        if ( operationDto == null ) {
            return;
        }

        if ( operation.getPatient() == null ) {
            operation.setPatient( new Patient() );
        }
        operationDtoToPatient1( operationDto, operation.getPatient() );
        if ( operation.getDoctor() == null ) {
            operation.setDoctor( new Doctor() );
        }
        operationDtoToDoctor1( operationDto, operation.getDoctor() );
        operation.setOperationDate( operationDto.getOperationDate() );
        operation.setDetails( operationDto.getDetails() );
    }

    private Long operationPatientPatient_id(Operation operation) {
        Patient patient = operation.getPatient();
        if ( patient == null ) {
            return null;
        }
        return patient.getPatient_id();
    }

    private Long operationDoctorDoctor_id(Operation operation) {
        Doctor doctor = operation.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        return doctor.getDoctor_id();
    }

    protected Patient operationDtoToPatient(OperationDto operationDto) {
        if ( operationDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setPatient_id( operationDto.getPatientId() );

        return patient;
    }

    protected Doctor operationDtoToDoctor(OperationDto operationDto) {
        if ( operationDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctor_id( operationDto.getDoctorId() );

        return doctor;
    }

    protected void operationDtoToPatient1(OperationDto operationDto, Patient mappingTarget) {
        if ( operationDto == null ) {
            return;
        }

        mappingTarget.setPatient_id( operationDto.getPatientId() );
    }

    protected void operationDtoToDoctor1(OperationDto operationDto, Doctor mappingTarget) {
        if ( operationDto == null ) {
            return;
        }

        mappingTarget.setDoctor_id( operationDto.getDoctorId() );
    }
}
