package com.medicalFront.domain;

public enum StatusVisit {
    RESERVATION ("RESERVATION"),
    FREE ("FREE"),
    DONE ("DONE");

    private String status;

    StatusVisit(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return status;
    }
}
