package com.medicalFront.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;


@Route
@PageTitle("Medical Reservation")
@Component
@UIScope
public class MainView extends AppLayout {

    private final NavigationBar menu;

    public MainView() {
        this.menu = new NavigationBar();

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");


        addToDrawer(menu);
        addToNavbar(toggle, title);
    }

}



