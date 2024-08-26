package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Appointment;
import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.dto.AppointmentDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T22:57:19-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setPatientId( appointmentPatientPatient_id( appointment ) );
        appointmentDto.setDoctorId( appointmentDoctorDoctor_id( appointment ) );
        appointmentDto.setAppointment_id( appointment.getAppointment_id() );
        appointmentDto.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentDto.setReason( appointment.getReason() );

        return appointmentDto;
    }

    @Override
    public Appointment toEntity(AppointmentDto appointmentDto) {
        if ( appointmentDto == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setPatient( appointmentDtoToPatient( appointmentDto ) );
        appointment.setDoctor( appointmentDtoToDoctor( appointmentDto ) );
        appointment.setAppointment_id( appointmentDto.getAppointment_id() );
        appointment.setAppointmentDate( appointmentDto.getAppointmentDate() );
        appointment.setReason( appointmentDto.getReason() );

        return appointment;
    }

    @Override
    public void updateEntityFromDTO(AppointmentDto appointmentDto, Appointment appointment) {
        if ( appointmentDto == null ) {
            return;
        }

        if ( appointment.getPatient() == null ) {
            appointment.setPatient( new Patient() );
        }
        appointmentDtoToPatient1( appointmentDto, appointment.getPatient() );
        if ( appointment.getDoctor() == null ) {
            appointment.setDoctor( new Doctor() );
        }
        appointmentDtoToDoctor1( appointmentDto, appointment.getDoctor() );
        appointment.setAppointmentDate( appointmentDto.getAppointmentDate() );
        appointment.setReason( appointmentDto.getReason() );
    }

    private Long appointmentPatientPatient_id(Appointment appointment) {
        Patient patient = appointment.getPatient();
        if ( patient == null ) {
            return null;
        }
        return patient.getPatient_id();
    }

    private Long appointmentDoctorDoctor_id(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        return doctor.getDoctor_id();
    }

    protected Patient appointmentDtoToPatient(AppointmentDto appointmentDto) {
        if ( appointmentDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setPatient_id( appointmentDto.getPatientId() );

        return patient;
    }

    protected Doctor appointmentDtoToDoctor(AppointmentDto appointmentDto) {
        if ( appointmentDto == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setDoctor_id( appointmentDto.getDoctorId() );

        return doctor;
    }

    protected void appointmentDtoToPatient1(AppointmentDto appointmentDto, Patient mappingTarget) {
        if ( appointmentDto == null ) {
            return;
        }

        mappingTarget.setPatient_id( appointmentDto.getPatientId() );
    }

    protected void appointmentDtoToDoctor1(AppointmentDto appointmentDto, Doctor mappingTarget) {
        if ( appointmentDto == null ) {
            return;
        }

        mappingTarget.setDoctor_id( appointmentDto.getDoctorId() );
    }
}
