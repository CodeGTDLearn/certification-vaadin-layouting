package com.certification.layouting.views.layouting;

import com.certification.layouting.views.layouting.project_solution.views.DashboardView;
import com.certification.layouting.views.layouting.project_solution.views.ProjectListView;
import com.certification.layouting.views.layouting.project_solution.views.TaskView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class ProjectManagementMainView extends AppLayout {
  public ProjectManagementMainView() {

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