package com.medicalFront.views;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Patient;
import com.medicalFront.service.PatientService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "Patients")
public class PatientView extends VerticalLayout {


    final NavigationBar menu;
    final Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));

    private PatientService patientService;
    final Grid grid;
    Button buttonNewObject = new Button("Add Patient");
    private Patient patient;
    Text titleText = new Text("Patients Service");



    public PatientView (PatientService service) {
        this.menu = new NavigationBar();
        this.patientService = service;
        this.grid = new Grid(Patient.class);
        grid.setColumns("firstName", "lastName", "email");

        buttonNewObject.addClickListener(e -> service.addPatient(patient));

        allPatientsList();

        HorizontalLayout buttonLayout = new HorizontalLayout(button,buttonNewObject);
        add(titleText, buttonLayout, menu, grid);

    }

    private void allPatientsList() {
        grid.setItems(patientService.getAllPatients().toArray(new Patient[0]));
    }

}
