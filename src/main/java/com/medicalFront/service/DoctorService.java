package com.medicalFront.service;

import com.medicalFront.client.DoctorClient;
import com.medicalFront.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private Set doctors;
    private static DoctorService doctorService;
    private final DoctorClient doctorClient;


    public static DoctorService getInstance() {
        if (doctorService == null) {
            doctorService = new DoctorService(DoctorClient.getInstance());
        }
        return doctorService;
    }


    public List<Doctor> getAllDoctors() {
        return doctorClient.getListAllDoctors();
    }

    public Doctor getDoctor(Long doctorId) {
        return doctorClient.getDoctor(doctorId);
    }

    public void addDoctor(Doctor doctor) {
        doctorClient.saveDoctor(doctor);
    }


    public void updateDoctor(Doctor doctor) {
        doctorClient.updateDoctor(doctor);
    }


    public void deleteDoctor(Doctor doctor) {
        doctorClient.deleteDoctor(doctor.getId());
    }


    public List findByLastName(String lastName) {
        return getAllDoctors().stream()
                .filter(patient -> patient.getLastName().contains(lastName))
                .collect(Collectors.toList());
    }

}
