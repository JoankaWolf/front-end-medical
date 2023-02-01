package com.medicalFront.client;

import com.medicalFront.domain.Patient;
import com.medicalFront.domain.Visit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PatientClient {
    private final RestTemplate restTemplate;
    private static final String PATIENT_URL = "http://localhost:8081/v1/medical/patient";
    private static PatientClient patientClient;

    public static PatientClient getInstance() {
        if (patientClient== null) {
            patientClient = new PatientClient(new RestTemplate());
        }
        return patientClient;
    }

    public List<Patient> getListAllPatient() {
        try {
            ResponseEntity<Patient[]> responseEntity = restTemplate.getForEntity(PATIENT_URL, Patient[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Patient getPatient(Long patientId) {
        try {
            return restTemplate.getForObject(PATIENT_URL + "/" + patientId, Patient.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void savePatient(Patient patient) {
        try {
            restTemplate.postForObject(PATIENT_URL, patient, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }


    public void updatePatient(Patient patient) {
        try {
            restTemplate.put(PATIENT_URL, patient, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }



    public void deletePatient(Long patientId) {
        try {
            restTemplate.delete(PATIENT_URL + "/" + patientId);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }

    public List<Visit> getAllVisitsForPatient(Long patientId) {
        try {
            ResponseEntity<Visit[]> responseEntity = restTemplate.getForEntity(PATIENT_URL + patientId, Visit[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
