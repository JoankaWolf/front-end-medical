package com.medicalFront.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
}
