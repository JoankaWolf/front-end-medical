package com.medicalFront.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private List<Visit> visits;


    @Override
    public String toString() {
        return "Dr " + firstName +
                " " + lastName +
                ", specialization: " + specialization;
    }
}
