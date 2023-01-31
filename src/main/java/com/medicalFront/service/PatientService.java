package com.medicalFront.service;

import com.medicalFront.client.PatientClient;
import com.medicalFront.domain.Patient;
import com.medicalFront.domain.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private static PatientService patientService;
    private final PatientClient patientClient;

    public static PatientService getInstance() {
        if (patientService == null) {
            patientService = new PatientService(PatientClient.getInstance());
        }
        return patientService;
    }

    public List<Patient> getAllPatients() {
        return patientClient.getListAllPatient();
    }

    public Patient getPatient(Long patientId) {
        return patientClient.getPatient(patientId);
    }

    public void addPatient(Patient patient) {
        patientClient.savePatient(patient);
    }

    public void updatePatient(Patient patient) {
        patientClient.updatePatient(patient);
    }

//    public void deletePatient(Long patientId) {
//        patientClient.deletePatient(patientId);
//    }

    public List<Visit> getAllVisitsForPatient(Long patientId) {
        return patientClient.getAllVisitsForPatient(patientId);
    }

    public void deletePatient(Patient patient) {
        Optional<Patient> searchedPatient = getAllPatients().stream()
                .filter(patientDto1 -> patientDto1.getId().equals(patient.getId()))
                .findFirst();
        patientClient.deletePatient(searchedPatient.get().getId());
    }
}
