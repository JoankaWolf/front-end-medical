package com.medicalFront.views.forms;

import com.medicalFront.domain.Patient;
import com.medicalFront.service.PatientService;
import com.medicalFront.views.PatientView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class PatientForm extends FormLayout {

    private PatientService service = PatientService.getInstance();
    private Binder<Patient> binder = new Binder<>(Patient.class);
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("email");
    private TextField peselNumber = new TextField("PESEL");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private PatientView patientView;

    public PatientForm(PatientView patientView) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(firstName, lastName, email, peselNumber, buttons);

        binder.bindInstanceFields(this);
        this.patientView = patientView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Patient patient = binder.getBean();
        service.addPatient(patient);
        refresh();
        setPatient(null);
    }

    private void delete() {
        Patient patient = binder.getBean();
        service.deletePatient(patient);
        refresh();
        setPatient(null);
    }

    public void setPatient(Patient patient) {
        binder.setBean(patient);

        if (patient == null) {
            setVisible(false);
        } else {
            setVisible(true);
            lastName.focus();
        }
    }
    public void refresh() {
        patientView.refresh();
    }

}

