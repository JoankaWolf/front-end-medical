package com.medicalFront.views;

import com.medicalFront.domain.Visit;
import com.medicalFront.service.VisitService;
import com.medicalFront.views.forms.VisitForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;


@Route(value = "Visits")
public class VisitView extends VerticalLayout {
    private DatePicker filter = new DatePicker();
    private Grid<Visit> grid = new Grid<>(Visit.class);
    private Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));
    private VisitService service = VisitService.getInstance();
    private Button buttonNewObject = new Button("New Visit");
    private HorizontalLayout buttonLayout = new HorizontalLayout(button, buttonNewObject, filter);
    private Text titleText = new Text("Visits Service");

    private VisitForm form = new VisitForm(this);

    public VisitView(VisitService service) {
        NavigationBar menu = new NavigationBar();
        filter.setPlaceholder("Filter by Date...");
        filter.setClearButtonVisible(true);
        filter.getValue();
        filter.addValueChangeListener(e -> update());
        grid.setColumns("appointmentDate", "appointmentTime", "price", "notes", "isPaid", "statusVisit", "doctor", "patient" );

        grid.setItems(service.getAllVisits());

        add(titleText, buttonLayout, menu, grid);

        buttonNewObject.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setVisit(new Visit());
        });


        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(mainContent);
        form.setVisit(null);
        setSizeFull();
        refresh();


        grid.asSingleSelect().addValueChangeListener(event -> form.setVisit(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(service.getAllVisits());
    }

    private void update() {
        grid.setItems(service.findByDate(filter.getLocale()));
    }

}