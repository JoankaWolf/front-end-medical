package com.medicalFront.views;

import com.medicalFront.domain.Doctor;
import com.medicalFront.domain.Patient;
import com.medicalFront.domain.Visit;
import com.medicalFront.service.DoctorService;
import com.medicalFront.views.forms.DoctorForm;
import com.medicalFront.views.forms.PatientForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route(value = "Doctors")
public class DoctorView extends VerticalLayout {

    private TextField filter = new TextField();
    private Grid<Doctor> grid = new Grid<>(Doctor.class);
    final Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));
    private Text titleText = new Text("Doctors Service");
    private DoctorService service = DoctorService.getInstance();
    private Button buttonNewObject = new Button("Add Doctor");
    private HorizontalLayout buttonLayout = new HorizontalLayout(button, buttonNewObject, filter);
    private DoctorForm form = new DoctorForm(this);

    public DoctorView () {
        NavigationBar menu = new NavigationBar();
        filter.setPlaceholder("Filter by Last Name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());

        grid.setColumns("firstName", "lastName", "specialization", "visits");
        grid.setItems(service.getAllDoctors());

        add(titleText, buttonLayout, menu, grid);

        buttonNewObject.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setDoctor(new Doctor());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
        form.setDoctor(null);
        setSizeFull();
        refresh();


        grid.asSingleSelect().addValueChangeListener(event -> form.setDoctor(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(service.getAllDoctors());
    }

    private void update() {
        grid.setItems(service.findByLastName(filter.getValue()));
    }

}
