package com.medicalFront.views;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Visit;
import com.medicalFront.service.DoctorService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route(value = "Doctors")
public class DoctorView extends VerticalLayout {

    final NavigationBar menu;
    final Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));

    private DoctorService doctorService;
    final Grid grid;
    Button buttonNewObject = new Button("Add Doctor");
    private Doctor doctor;




    public DoctorView (DoctorService service) {
        this.menu = new NavigationBar();
        this.doctorService = service;
        this.grid = new Grid(Doctor.class);
        grid.setColumns("firstName", "lastName", "specialization");

        buttonNewObject.addClickListener(e -> service.addDoctor(doctor));

        allDoctorsList();


        add(button,menu, buttonNewObject, grid);
    }

    private void allDoctorsList() {
        grid.setItems(doctorService.getAllDoctors().toArray(new Doctor[0]));
    }

}
