package com.medicalFront.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private long peselNumber;

}
