package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.BillDto;
import com.example.HospitalManagement.Service.BillService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<BillDto> createBill(@RequestBody BillDto billDto) {
        BillDto createdBill = billService.createBill(billDto);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDto> getBillById(@PathVariable("billId") Long billId) {
        BillDto billDto = billService.getBillById(billId);
        return ResponseEntity.ok(billDto);
    }

    @GetMapping
    public ResponseEntity<List<BillDto>> getAllBills() {
        List<BillDto> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<BillDto> updateBill(@PathVariable("billId") Long billId, @RequestBody BillDto billDto) {
        BillDto updatedBill = billService.updateBill(billId, billDto);
        return ResponseEntity.ok(updatedBill);
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable("billId") Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/download/{billId}")
    public ResponseEntity<byte[]> downloadBookingReceipt(@PathVariable Long billId) throws DocumentException, IOException {
        ByteArrayInputStream receiptStream = billService.generateBillReceipt(billId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=booking_receipt.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(receiptStream.readAllBytes());
    }
}
