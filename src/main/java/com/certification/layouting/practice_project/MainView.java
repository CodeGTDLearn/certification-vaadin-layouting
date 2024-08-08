package com.certification.layouting.practice_project;

import com.certification.layouting.practice_project.projectOverview.ProjectOverviewView;
import com.certification.layouting.practice_project.project.ProjectsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import static com.certification.layouting.practice_project.config.AppRoutes.PROJECT_ROOT_ROUTE;
import static com.certification.layouting.practice_project.config.AppTitles.*;

@PageTitle(CERTIFICATION_LESSON_TITLE)
@Route(value = PROJECT_ROOT_ROUTE)
public class MainView extends AppLayout {
  public MainView() {

    addToNavbar(
         new DrawerToggle(),
         new H2(MAIN_VIEW_TITLE)
    );

    var verticalLayout = new VerticalLayout(
         new RouterLink(OVERVIEW_MENU_TITLE, ProjectOverviewView.class),
         new RouterLink(PROJECT_MENU_TITLE, ProjectsView.class)
    );


    addToDrawer(verticalLayout);
  }
}