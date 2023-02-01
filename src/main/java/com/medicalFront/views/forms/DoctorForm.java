package com.medicalFront.views.forms;

import com.medicalFront.domain.Doctor;
import com.medicalFront.service.DoctorService;
import com.medicalFront.views.DoctorView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class DoctorForm extends FormLayout {
    private DoctorService service = DoctorService.getInstance();
    private Binder<Doctor> binder = new Binder<>(Doctor.class);
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField specialization = new TextField("Specialization");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private DoctorView doctorView;

    public DoctorForm (DoctorView doctorView) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(firstName, lastName, specialization, buttons);

        binder.bindInstanceFields(this);
        this.doctorView = doctorView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Doctor doctor= binder.getBean();
        service.addDoctor(doctor);
        refresh();
        setDoctor(null);
    }

    private void delete() {
        Doctor doctor = binder.getBean();
        service.deleteDoctor(doctor);
        refresh();
        setDoctor(null);
    }

    public void setDoctor(Doctor doctor) {
        binder.setBean(doctor);

        if (doctor == null) {
            setVisible(false);
        } else {
            setVisible(true);
            lastName.focus();
        }
    }
    public void refresh() {
        doctorView.refresh();
    }

}
