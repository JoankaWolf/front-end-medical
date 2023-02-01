package com.medicalFront.client;

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
public class VisitClient {

    private final RestTemplate restTemplate;
    private static final String VISIT_URL = "http://localhost:8081/v1/medical/visit";
    private static VisitClient visitClient;

    public static VisitClient getInstance() {
        if(visitClient == null) {
            return visitClient = new VisitClient(new RestTemplate());
        }
        return visitClient;
    }

    public List<Visit> getListAllVisit() {
        try {
            ResponseEntity<Visit[]> responseEntity = restTemplate.getForEntity(VISIT_URL, Visit[].class);
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Visit getVisit(Long visitId) {
        try {
            return restTemplate.getForObject(VISIT_URL + "/" + visitId, Visit.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void saveVisit(Visit visit) {
        try {
            restTemplate.postForObject(VISIT_URL, visit, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }


    public void updateVisit(Visit visit) {
        try {
            restTemplate.put(VISIT_URL,visit, Void.class);
        }
        catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }



    public void deleteVisit(Long visitId) {
        try {
            restTemplate.delete(VISIT_URL + "/" + visitId);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
    }

}
