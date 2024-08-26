package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Bill;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.dto.BillDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-25T22:57:18-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Override
    public BillDto toDto(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setPatientId( billPatientPatient_id( bill ) );
        billDto.setBillId( bill.getBillId() );
        billDto.setAmount( bill.getAmount() );
        billDto.setBillDate( bill.getBillDate() );
        billDto.setDescription( bill.getDescription() );

        return billDto;
    }

    @Override
    public Bill toEntity(BillDto billDto) {
        if ( billDto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setPatient( billDtoToPatient( billDto ) );
        bill.setBillId( billDto.getBillId() );
        bill.setAmount( billDto.getAmount() );
        bill.setBillDate( billDto.getBillDate() );
        bill.setDescription( billDto.getDescription() );

        return bill;
    }

    @Override
    public void updateEntityFromDTO(BillDto billDto, Bill bill) {
        if ( billDto == null ) {
            return;
        }

        if ( bill.getPatient() == null ) {
            bill.setPatient( new Patient() );
        }
        billDtoToPatient1( billDto, bill.getPatient() );
        bill.setBillId( billDto.getBillId() );
        bill.setAmount( billDto.getAmount() );
        bill.setBillDate( billDto.getBillDate() );
        bill.setDescription( billDto.getDescription() );
    }

    private Long billPatientPatient_id(Bill bill) {
        Patient patient = bill.getPatient();
        if ( patient == null ) {
            return null;
        }
        return patient.getPatient_id();
    }

    protected Patient billDtoToPatient(BillDto billDto) {
        if ( billDto == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setPatient_id( billDto.getPatientId() );

        return patient;
    }

    protected void billDtoToPatient1(BillDto billDto, Patient mappingTarget) {
        if ( billDto == null ) {
            return;
        }

        mappingTarget.setPatient_id( billDto.getPatientId() );
    }
}
