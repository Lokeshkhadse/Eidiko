package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.dto.PaymentDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T22:57:19-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDto toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setPatientId( paymentPatientPatient_id( payment ) );
        if ( payment.getAmountPaid() != null ) {
            paymentDto.setAmountPaid( Double.parseDouble( payment.getAmountPaid() ) );
        }
        paymentDto.setPaymentDate( payment.getPaymentDate() );
        paymentDto.setPaymentMethod( payment.getPaymentMethod() );

        return paymentDto;
    }

    @Override
    public Payment toEntity(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setPatient( paymentDtoToPatient( paymentDto ) );
        payment.setAmountPaid( String.valueOf( paymentDto.getAmountPaid() ) );
        payment.setPaymentDate( paymentDto.getPaymentDate() );
        payment.setPaymentMethod( paymentDto.getPaymentMethod() );

        return payment;
    }

    @Override
    public void updateEntityFromDTO(PaymentDto paymentDto, Payment payment) {
        if ( paymentDto == null ) {
            return;
        }

        if ( payment.getPatient() == null ) {
            payment.setPatient( new Patient() );
        }
        paymentDtoToPatient1( paymentDto, payment.getPatient() );
        payment.setAmountPaid( String.valueOf( paymentDto.getAmountPaid() ) );
        payment.setPaymentDate( paymentDto.getPaymentDate() );
        payment.setPaymentMethod( paymentDto.getPaymentMethod() );
    }

    private Long paymentPatientPatient_id(Payment payment) {
        Patient patient = payment.getPatient();
        if ( patient == null ) {
            return null;
        }
        return patient.getPatient_id();
    }

    protected Patient paymentDtoToPatient(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setPatient_id( paymentDto.getPatientId() );

        return patient;
    }

    protected void paymentDtoToPatient1(PaymentDto paymentDto, Patient mappingTarget) {
        if ( paymentDto == null ) {
            return;
        }

        mappingTarget.setPatient_id( paymentDto.getPatientId() );
    }
}
