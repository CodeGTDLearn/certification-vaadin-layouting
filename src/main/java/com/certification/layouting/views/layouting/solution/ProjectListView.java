package com.certification.layouting.views.layouting.solution;

import com.certification.layouting.views.layouting.ProjectManagementView;
import com.certification.layouting.views.layouting.entity.Project;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.vaadin.flow.component.icon.VaadinIcon.EDIT;
import static com.vaadin.flow.component.icon.VaadinIcon.PLUS;
import static java.time.LocalDate.now;

@Route(value = "projects", layout = ProjectManagementView.class)
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
    filterField.addValueChangeListener(e -> mockedDatasourceUpdateList());

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

    mockedDatasourceUpdateList();
  }

  // Aqui você normalmente buscaria os dados do seu backend
  // Para este exemplo, vamos criar alguns projetos de exemplo
  private void mockedDatasourceUpdateList() {

    List<Project> projects = Arrays.asList(
         new Project(
              "Project A",
              now(),
              now().plusMonths(3),
              "On going"
         ),
         new Project(
              "Project B",
              now().minusMonths(1),
              now().plusMonths(2),
              "On going"
         ),
         new Project(
              "Project C",
              now().plusMonths(1),
              now().plusMonths(6),
              "Planned"
         )
    );

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