package com.medicalFront.client;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class DoctorClient {

    private final RestTemplate restTemplate;
    private static final String DOCTOR_URL = "http://localhost:8081/v1/medical/doctor";
    private static DoctorClient doctorClient;

    public static DoctorClient getInstance() {
        if (doctorClient== null) {
            doctorClient = new DoctorClient(new RestTemplate());
        }
        return doctorClient;
    }

    public List<Doctor> getListAllDoctors() {
        try {
            ResponseEntity<Doctor[]> responseEntity = restTemplate.getForEntity(DOCTOR_URL, Doctor[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public Doctor getDoctor(Long doctorId) {
        try {
            return restTemplate.getForObject(DOCTOR_URL + "/" + doctorId, Doctor.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    public void saveDoctor(Doctor doctor) {
        try {
            restTemplate.postForObject(DOCTOR_URL, doctor, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }


    public void updateDoctor(Doctor doctor) {
        try {
            restTemplate.put(DOCTOR_URL, doctor, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }


    public void deleteDoctor(Long doctorId) {
        try {
            restTemplate.delete(DOCTOR_URL + "/" + doctorId);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }
}
