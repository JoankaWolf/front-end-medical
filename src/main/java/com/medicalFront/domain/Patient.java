package com.medicalFront.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long peselNumber;
    private List<Visit> visits;

    @Override
    public String toString() {
        return "Patient: " +
                firstName +
                ", " + lastName +
                ", peselNumber: " + peselNumber;
    }
}
