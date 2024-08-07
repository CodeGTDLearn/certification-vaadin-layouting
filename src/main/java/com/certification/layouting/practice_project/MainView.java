package com.certification.layouting.practice_project;

import com.certification.layouting.practice_project.dashboard.DashboardView;
import com.certification.layouting.practice_project.project.ProjectListView;
import com.certification.layouting.practice_project.projecttask.ProjectTaskView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import static com.certification.layouting.practice_project.config.AppRoutes.PROJECT_ROOT_ROUTE;
import static com.certification.layouting.practice_project.config.AppTitles.*;

@PageTitle(CERTIFICATION_LESSON)
@Route(value = PROJECT_ROOT_ROUTE)
public class MainView extends AppLayout {
  public MainView() {

    addToNavbar(
         new DrawerToggle(),
         new H2(MAIN_VIEW_TITLE)
    );

    addToDrawer(
         new VerticalLayout(
              new RouterLink(DASHBOARD_MENU_TITLE, DashboardView.class),
              new RouterLink(PROJECT_MENU_TITLE, ProjectListView.class),
              new RouterLink(PROJECT_TASK_MENU_TITLE, ProjectTaskView.class)
         )
    );
  }
}