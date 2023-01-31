package com.medicalFront;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Patient;
import com.medicalFront.domain.Rating;
import com.medicalFront.domain.Visit;
import com.medicalFront.service.DoctorService;
import com.medicalFront.service.PatientService;
import com.medicalFront.service.VisitService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Route("reservation")
@Component
@PageTitle("Medical Reservation")
@UIScope
public class ReservationView extends VerticalLayout {




    private DatePicker visitDate = new DatePicker("Visit Date");

    public ReservationView() {

        HorizontalLayout dates = new HorizontalLayout(visitDate);
        Text moreDetails = new Text("Date");
        add(moreDetails,dates);
        lookDates();


    }

    private void lookDates() {
        visitDate.setMin(LocalDate.now());
        visitDate.addValueChangeListener(e -> visitDate.setMin(e.getValue()));

    }


}


