package com.medicalFront.views.forms;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Patient;
import com.medicalFront.domain.StatusVisit;
import com.medicalFront.domain.Visit;
import com.medicalFront.service.DoctorService;
import com.medicalFront.service.PatientService;
import com.medicalFront.service.VisitService;
import com.medicalFront.views.VisitView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

import java.time.Duration;
import java.time.LocalTime;

public class VisitForm extends FormLayout {

    private VisitService service = VisitService.getInstance();
    private Binder<Visit> binder = new Binder<>(Visit.class);
    private DatePicker appointmentDate = new DatePicker("Appointment Date");
    private TimePicker appointmentTime = new TimePicker("Appointment Time");
    private TextField price = new TextField("Price");
    private TextField notes = new TextField("Notes");
    private ComboBox<Boolean> isPaid = new ComboBox<>("is Paid");
    private ComboBox <StatusVisit> statusVisit = new ComboBox<>("Status Visit");
    private ComboBox <Doctor> doctor = new ComboBox<>("Doctor");
    private ComboBox<Patient> patient = new ComboBox<>("Patient");
//
    Binder<Doctor> binderD = new Binder<>(Doctor.class);
//    Binder<Patient> binderP = new Binder<>(Patient.class);
    private Div euroSuffix = new Div();

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private VisitView visitView;
    private DoctorService doctorService = DoctorService.getInstance();
    private PatientService patientService = PatientService.getInstance();

    public VisitForm(VisitView visitView) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        appointmentTime.setMin(LocalTime.of(8, 00));
        appointmentTime.setMax(LocalTime.of(18, 40));
        appointmentTime.setStep(Duration.ofMinutes(20));
        euroSuffix.setText("€");
        price.setSuffixComponent(euroSuffix);
        statusVisit.setItems(StatusVisit.values());
        isPaid.setItems(true, false);
//
//        binderD.forField(doctor).bind(Doctor::getId,Doctor::setId);
//
//        binderP.forField(patient).bind(Patient::getId, Patient::setId);

//        ComboBox<Long> doctor = new ComboBox<>("Doctor");
//
//        ComboBox.ItemFilter<Doctor> filter = (lastName, filterString) ->lastName.getLastName()
//                .toLowerCase().startsWith(filterString.toLowerCase());
//        doctor.setItems(filter, doctorService.getAllDoctors());
//
//        add(doctor);
//        doctor.setItemLabelGenerator(Doctor::getLastName);
        patient.setItems(patientService.getAllPatients());
        doctor.setItems(doctorService.getAllDoctors());
   //     binderD.forField(doctor).withConverter(v -> extractID(v), v -> findId(doctorService, v)).bind(Doctor::getId, Doctor::setId);



        add(appointmentDate, appointmentTime, price, notes, isPaid, statusVisit, doctor, patient,
                 buttons);

        binder.bindInstanceFields(this);
        this.visitView = visitView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Visit visit = binder.getBean();
        service.addVisit(visit);
        refresh();
        setVisit(null);
    }

    private void delete() {
        Visit visit = binder.getBean();
        service.deleteVisit(visit.getId());
        refresh();
        setVisit(null);
    }

    public void setVisit(Visit visit) {
        binder.setBean(visit);

        if (visit == null) {
            setVisible(false);
        } else {
            setVisible(true);
            price.focus();
        }
    }


    public void refresh() {
        visitView.refresh();
    }


    private Long extractID(Doctor v) {
        return (v == null) ? null : v.getId();
    }

    private Doctor findId(DoctorService doctorService, Long id) {
        // User selected blank, return blank
        if (id == null)
            return new Doctor();
        // Change this to properly interface with your service
        return doctorService.getDoctor(id);
    }





}
