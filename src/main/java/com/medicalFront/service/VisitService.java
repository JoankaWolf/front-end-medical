package com.medicalFront.service;

import com.medicalFront.client.VisitClient;
import com.medicalFront.domain.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private static VisitService visitService;
    private final VisitClient visitClient;

    public static VisitService getInstance() {
        if(visitService == null) {
            visitService = new VisitService(VisitClient.getInstance());
        }
        return visitService;
    }

    public List<Visit> getAllVisits() {
        return  visitClient.getListAllVisit();
    }

    public Visit getVisit(Long visitId) {
        return visitClient.getVisit(visitId);
    }

    public void addVisit(Visit visit) {
        visitClient.saveVisit(visit);
    }

    public void updateVisit(Visit visit) {
        visitClient.updateVisit(visit);
    }

    public void deleteVisit(Long visitId) {
        visitClient.deleteVisit(visitId);
    }
}
