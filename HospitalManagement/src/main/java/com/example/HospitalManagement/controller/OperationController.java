package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.dto.OperationDto;
import com.example.HospitalManagement.Service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping
    public ResponseEntity<OperationDto> createOperation(@RequestBody OperationDto operationDto) {
        OperationDto createdOperation = operationService.createOperation(operationDto);
        return new ResponseEntity<>(createdOperation, HttpStatus.CREATED);
    }

    @GetMapping("/{operation_id}")
    public ResponseEntity<OperationDto> getOperationById(@PathVariable("operation_id") Long operation_id) {
        OperationDto operationDto = operationService.getOperationById(operation_id);
        return ResponseEntity.ok(operationDto);
    }

    @GetMapping
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        List<OperationDto> operations = operationService.getAllOperations();
        return ResponseEntity.ok(operations);
    }

    @PutMapping("/{operation_id}")
    public ResponseEntity<OperationDto> updateOperation(@PathVariable("operation_id") Long operation_id, @RequestBody OperationDto operationDto) {
        OperationDto updatedOperation = operationService.updateOperation(operation_id, operationDto);
        return ResponseEntity.ok(updatedOperation);
    }

    @DeleteMapping("/{operation_id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable("operation_id") Long operation_id) {
        operationService.deleteOperation(operation_id);
        return ResponseEntity.noContent().build();
    }
}

