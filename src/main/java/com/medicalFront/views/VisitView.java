package com.medicalFront.views;

import com.medicalFront.domain.Visit;
import com.medicalFront.service.VisitService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "Visits")
public class VisitView extends VerticalLayout {

    final NavigationBar menu;
    final Button button = new Button("Main Menu", event -> UI.getCurrent().navigate(""));

    private VisitService service;
    final Grid grid;
    Button buttonNewObject = new Button("New Visit");
   private Visit visit;

    public VisitView(VisitService service) {

        this.menu = new NavigationBar();
        this.service = service;
        this.grid = new Grid<>(Visit.class);
        grid.setColumns("notes", "price", "doctor", "patient");

        buttonNewObject.addClickListener(e -> service.addVisit(visit));

        allVisitsList();

        add(button,menu, buttonNewObject, grid);
    }

    private void allVisitsList() {
        grid.setItems(service.getAllVisits().toArray(new Visit[0]));
    }

}