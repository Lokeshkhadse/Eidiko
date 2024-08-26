package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Operation;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.OperationRepository;
import com.example.HospitalManagement.dto.OperationDto;
import com.example.HospitalManagement.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public OperationDto createOperation(OperationDto operationDto) {
        Operation operation = operationMapper.toEntity(operationDto);
        Operation savedOperation = operationRepository.save(operation);
        return operationMapper.toDTO(savedOperation);
    }

    @Override
    public OperationDto getOperationById(Long operation_id) {
        Operation operation = operationRepository.findById(operation_id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));
        return operationMapper.toDTO(operation);
    }

    @Override
    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll().stream()
                .map(operationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto updateOperation(Long operation_id, OperationDto operationDto) {
        Operation existingOperation = operationRepository.findById(operation_id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));

        operationMapper.updateEntityFromDTO(operationDto, existingOperation);
        Operation updatedOperation = operationRepository.save(existingOperation);
        return operationMapper.toDTO(updatedOperation);
    }

    @Override
    public void deleteOperation(Long operation_id) {
        if (!operationRepository.existsById(operation_id)) {
            throw new ResourceNotFoundException("Operation not found with ID: " + operation_id);
        }
        operationRepository.deleteById(operation_id);
    }
}
