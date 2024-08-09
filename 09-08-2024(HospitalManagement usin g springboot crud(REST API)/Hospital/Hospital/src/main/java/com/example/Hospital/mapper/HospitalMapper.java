package com.example.Hospital.mapper;

import com.example.Hospital.dto.HospitalDto;
import com.example.Hospital.entity.Hospital;

public class HospitalMapper {

    public static HospitalDto mapToHospitalDto(Hospital hospital){
        return new HospitalDto(
                hospital.getPatientid(),
                hospital.getName(),
                hospital.getDob(),
                hospital.getGender(),
                hospital.getContactno(),
                hospital.getMedicalhistory(),
                hospital.getDocName()

        );
    }

    public static Hospital mapToHospital(HospitalDto hospitalDto){ // it map hospitaldetail to hospitalDto
        return new Hospital(
                hospitalDto.getPatientid(),
                hospitalDto.getName(),
                hospitalDto.getDob(),
                hospitalDto.getGender(),
                hospitalDto.getContactno(),
                hospitalDto.getMedicalhistory(),
                hospitalDto.getDocName()
        );
    }





}
