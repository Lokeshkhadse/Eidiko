package com.example.Hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patientid;
    private String name;
    private Date dob;
    private String gender;
    private String contactno;
    private String medicalhistory;
    private String docName;

    @OneToMany(mappedBy = "patientid" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FileEntity> files = new ArrayList<>();

    public Hospital(Long patientid, String name, Date dob, String gender, String contactno, String medicalhistory, String docName) {

        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactno = contactno;
        this.medicalhistory = medicalhistory;
        this.docName = docName;
    }
}
