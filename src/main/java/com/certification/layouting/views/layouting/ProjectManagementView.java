package com.certification.layouting.views.layouting;

import com.certification.layouting.views.layouting.solution.DashboardView;
import com.certification.layouting.views.layouting.solution.ProjectListView;
import com.certification.layouting.views.layouting.solution.TaskView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class ProjectManagementView extends AppLayout {
  public ProjectManagementView() {

    addToNavbar(
         new DrawerToggle(),
         new H2("Project Management")
    );


    addToDrawer(
         new VerticalLayout(
              new RouterLink("Dashboard", DashboardView.class),
              new RouterLink("Projects", ProjectListView.class),
              new RouterLink("ProjectTask", TaskView.class)
         )
    );
  }
}