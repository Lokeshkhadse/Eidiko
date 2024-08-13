package com.example.Hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    private String filename;
    private String filetype;

    @Lob
    @Column(name = "data" , columnDefinition = "LONGBLOB")
    private byte[] data;


    private LocalDateTime uploaddate;

    @ManyToOne
    @JoinColumn(name = "patientid" , nullable = false)
    private Hospital patientid;

}
