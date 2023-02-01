package com.medicalFront.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visit {

    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private double price;
    private String notes;
    private Boolean isPaid;
    private StatusVisit statusVisit;
    private Long doctor;
    private Long patient;

}
