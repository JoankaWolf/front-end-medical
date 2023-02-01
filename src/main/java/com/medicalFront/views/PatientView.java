package com.medicalFront.views;

import com.medicalFront.domain.Patient;
import com.medicalFront.service.PatientService;
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

@Route(value = "Patients")
public class PatientView extends VerticalLayout {

    private TextField filter = new TextField();
    private Grid<Patient> grid = new Grid<>(Patient.class);
    private Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));
    private PatientService service = PatientService.getInstance();
    private Button buttonNewObject = new Button("Add Patient");
    private Text titleText = new Text("Patients Service");
    private HorizontalLayout buttonLayout = new HorizontalLayout(button, buttonNewObject, filter);


    private PatientForm form = new PatientForm(this);
    public PatientView () {
        NavigationBar menu = new NavigationBar();
        filter.setPlaceholder("Filter by Last Name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("firstName", "lastName", "email", "peselNumber", "visits");
        grid.setItems(service.getAllPatients());

        add(titleText, buttonLayout, menu, grid);

        buttonNewObject.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setPatient(new Patient());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
        form.setPatient(null);
        setSizeFull();
        refresh();


        grid.asSingleSelect().addValueChangeListener(event -> form.setPatient(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(service.getAllPatients());
    }

    private void update() {
        grid.setItems(service.findByLastName(filter.getValue()));
    }


}
