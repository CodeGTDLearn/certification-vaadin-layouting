package com.certification.layouting.practice_project.project;

import com.certification.layouting.practice_project.MainView;
import com.certification.layouting.practice_project.utils.MockedDataSource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Collectors;

import static com.certification.layouting.practice_project.config.AppRoutes.PROJECT_ROUTE;
import static com.vaadin.flow.component.icon.VaadinIcon.EDIT;
import static com.vaadin.flow.component.icon.VaadinIcon.PLUS;

@Route(value = PROJECT_ROUTE, layout = MainView.class)
public class ProjectsView extends VerticalLayout {

  private Grid<Project> projectGrid = new Grid<>(Project.class);
  private TextField filterProjectField = new TextField();

  public ProjectsView() {

    setSizeFull();
    setPadding(true);
    setSpacing(true);

    var toolbar = new HorizontalLayout();
    toolbar.add(filterProjectField, addProjectButton());

    setFilterProjectField();
    setProjectGrid(findProjectsByFilter(filterProjectField.getValue()
                                                          .toLowerCase()));

    add(toolbar, filterProjectField, projectGrid);
  }

  private void setFilterProjectField() {

    filterProjectField.setPlaceholder("Filter by name...");
    filterProjectField.setClearButtonVisible(true);
    filterProjectField
         .addValueChangeListener(
              e -> {
                var projects =
                     findProjectsByFilter(
                          filterProjectField.getValue()
                                            .toLowerCase());

                projectGrid.setItems(projects);
              });

  }

  private void setProjectGrid(List<Project> projects) {

    projectGrid.setColumns("name", "startDate", "endDate", "status");

    projectGrid
         .getColumnByKey("name")
         .setHeader("Project");

    projectGrid
         .getColumnByKey("startDate")
         .setHeader("Start Date");

    projectGrid
         .getColumnByKey("endDate")
         .setHeader("End Date");

    projectGrid
         .getColumnByKey("status")
         .setHeader("Status");

    projectGrid
         .addComponentColumn(this::editProjectButton)
         .setHeader("Actions");

    projectGrid.setItems(projects);
  }

  private List<Project> findProjectsByFilter(String criteria) {

    var filteredProjects = MockedDataSource.projectList;

    if (criteria != null && ! criteria.isEmpty()) {
      filteredProjects = filteredProjects
           .stream()
           .filter(
                project ->
                     project
                          .getName()
                          .toLowerCase()
                          .contains(criteria))
           .collect(Collectors.toList());
    }

    return filteredProjects;
  }

  private Button addProjectButton() {

    var button = new Button("Add Project", new Icon(PLUS));

    button.addClickListener(e -> {
      final String text = "Addition Project Functionality not implemented yet";
      Notification.show(text, 3000, Notification.Position.MIDDLE);
    });

    return button;
  }

  private Button editProjectButton(Project project) {

    var button = new Button("Edit", new Icon(EDIT));

    button.addClickListener(e -> {
      final String text = "Updating project: " + project.getName();
      Notification.show(text, 3000, Notification.Position.MIDDLE);
    });

    return button;
  }
}