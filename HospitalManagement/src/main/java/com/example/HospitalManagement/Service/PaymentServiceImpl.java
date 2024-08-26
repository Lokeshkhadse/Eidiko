package com.example.HospitalManagement.Service;


import com.example.HospitalManagement.Entity.Bill;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.Repository.PaymentRepository;
import com.example.HospitalManagement.mapper.PaymentMapper;

import com.example.HospitalManagement.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        if (paymentDto.getPatientId() != null) {
            Optional<Patient> patient = patientRepository.findById(paymentDto.getPatientId());
            patient.ifPresent(payment::setPatient);
        }
        payment = paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            paymentMapper.updateEntityFromDTO(paymentDto, payment);
            if (paymentDto.getPatientId() != null) {
                Optional<Patient> patient = patientRepository.findById(paymentDto.getPatientId());
                patient.ifPresent(payment::setPatient);
            }
            payment = paymentRepository.save(payment);
            return paymentMapper.toDto(payment);
        }
        return null; // or throw an exception
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElse(null); // or throw an exception
    }

    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return  payments.stream().map((payment) -> paymentMapper.toDto(payment)).collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

