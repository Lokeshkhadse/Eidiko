package com.example.Hospital.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {     //it is use to send data between cilent and server
                                 // means it show to user

    private Long patientid;

    private String name;
    private Date dob;
    private String gender;
    private String contactno;
    private String medicalhistory;
    private String docName;
}
