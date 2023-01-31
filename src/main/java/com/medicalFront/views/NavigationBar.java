package com.medicalFront.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class NavigationBar extends HorizontalLayout {
    public NavigationBar() {

        add(getTabs());
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTabVisits(),
                createTabPatients(),
                createTabDoctors());

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTabVisits() {
        Icon icon = VaadinIcon.CALENDAR.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Visits"));

        link.setRoute(VisitView.class);
        link.setTabIndex(-1);

        return new Tab(link);

    }
    private Tab createTabDoctors() {
        Icon icon = VaadinIcon.STETHOSCOPE.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Doctors"));

        link.setRoute(DoctorView.class);
        link.setTabIndex(0);

        return new Tab(link);

    }
    private Tab createTabPatients() {
        Icon icon = VaadinIcon.USER_HEART.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span("Patients"));

        link.setRoute(PatientView.class);
        link.setTabIndex(1);

        return new Tab(link);

    }
}
