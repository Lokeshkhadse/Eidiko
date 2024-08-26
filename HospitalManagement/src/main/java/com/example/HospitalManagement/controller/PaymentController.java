package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.PaymentDto;
import com.example.HospitalManagement.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("payment_id") Long payment_id) {
        PaymentDto paymentDto = paymentService.getPaymentById(payment_id);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{payment_id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable("payment_id") Long payment_id, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatedPayment = paymentService.updatePayment(payment_id, paymentDto);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{payment_id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("payment_id") Long payment_id) {
        paymentService.deletePayment(payment_id);
        return ResponseEntity.noContent().build();
    }
}
