package com.certification.layouting.practice_project.project;

import com.certification.layouting.practice_project.MainView;
import com.certification.layouting.practice_project.config.MockedDataSource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

import static com.certification.layouting.practice_project.config.AppRoutes.PROJECT_ROUTE;
import static com.vaadin.flow.component.icon.VaadinIcon.EDIT;
import static com.vaadin.flow.component.icon.VaadinIcon.PLUS;
import static java.time.LocalDate.now;

@Route(value = PROJECT_ROUTE, layout = MainView.class)
public class ProjectListView extends VerticalLayout {
  private Grid<Project> projectGrid;
  private TextField filterField;

  public ProjectListView() {

    setSizeFull();
    setPadding(true);
    setSpacing(true);

    createToolbar();
    createProjectGrid();

    add(filterField, projectGrid);
  }

  private void createToolbar() {

    HorizontalLayout toolbar = new HorizontalLayout();
    filterField = new TextField();
    filterField.setPlaceholder("Filter by name...");
    filterField.setClearButtonVisible(true);
    filterField.addValueChangeListener(e -> fetchList());

    Button addButton = new Button("Add Project", new Icon(PLUS));
    addButton.addClickListener(e -> addProject());

    toolbar.add(filterField, addButton);
    add(toolbar);
  }

  private void createProjectGrid() {

    projectGrid = new Grid<>(Project.class);
    projectGrid.setColumns("name", "startDate", "endDate", "status");
    projectGrid.getColumnByKey("name")
               .setHeader("Project Name");
    projectGrid.getColumnByKey("startDate")
               .setHeader("Start Date");
    projectGrid.getColumnByKey("endDate")
               .setHeader("End Date");
    projectGrid.getColumnByKey("status")
               .setHeader("Status");

    projectGrid.addColumn(
                    project -> {
                      var button = new Button("Edit", new Icon(EDIT));
                      button.addClickListener(e -> editProject(project));
                      return button;
                    })
               .setHeader("Actions");

    fetchList();
  }

  // Aqui você normalmente buscaria os dados do seu backend
  // Para este exemplo, vamos criar alguns projetos de exemplo
  private void fetchList() {

    var projects = MockedDataSource.mockedProjectList;

    if (filterField.getValue() != null && ! filterField.getValue()
                                                       .isEmpty()) {
      projects = projects.stream()
                         .filter(project -> project.getName()
                                                   .toLowerCase()
                                                   .contains(filterField.getValue()
                                                                        .toLowerCase()))
                         .collect(Collectors.toList());
    }
    projectGrid.setItems(projects);
  }

  private void addProject() {

    final String text = "Addition Project Functionality not implemented yet";
    Notification.show(text, 3000, Notification.Position.MIDDLE);
  }

  private void editProject(Project project) {

    final String text = "Updating project: " + project.getName();
    Notification.show(text, 3000, Notification.Position.MIDDLE);
  }
}