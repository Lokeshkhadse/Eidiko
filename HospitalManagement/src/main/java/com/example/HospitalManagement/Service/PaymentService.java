package com.example.HospitalManagement.Service;


import com.example.HospitalManagement.dto.PaymentDto;
import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
    void deletePayment(Long id);
}
