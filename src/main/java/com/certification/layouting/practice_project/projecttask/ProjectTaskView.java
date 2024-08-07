package com.certification.layouting.practice_project.projecttask;

import com.certification.layouting.practice_project.MainView;
import com.certification.layouting.practice_project.config.MockedDataSource;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

import static com.certification.layouting.practice_project.config.AppRoutes.PROJECT_TASK_ROUTE;
import static com.vaadin.flow.component.icon.VaadinIcon.CHECK;

@Route(value = PROJECT_TASK_ROUTE, layout = MainView.class)
public class ProjectTaskView extends VerticalLayout {

  private Grid<ProjectTask> taskGrid;
  private ComboBox<String> projectFilter;
  private ComboBox<String> statusFilter;

  public ProjectTaskView() {

    setSizeFull();
    setPadding(true);
    setSpacing(true);

    createFilters();
    createTaskGrid();

    var row = new HorizontalLayout(projectFilter, statusFilter);

    add(row, taskGrid);
  }

  private void createFilters() {

    projectFilter = new ComboBox<>("Project");
    projectFilter.setItems("All", "Project A", "Project B", "Project C");
    projectFilter.setValue("All");
    projectFilter.addValueChangeListener(e -> mockedDatasourceUpdateList());

    statusFilter = new ComboBox<>("Status");
    statusFilter.setItems("All", "Working on", "On going", "Concluded");
    statusFilter.setValue("All");
    statusFilter.addValueChangeListener(e -> mockedDatasourceUpdateList());
  }

  private void createTaskGrid() {

    taskGrid = new Grid<>(ProjectTask.class);

    taskGrid.setColumns("name", "project", "assignedTo", "dueDate", "status");

    taskGrid
         .getColumnByKey("name")
         .setHeader("Task");

    taskGrid.getColumnByKey("project")
            .setHeader("Project");

    taskGrid.getColumnByKey("assignedTo")
            .setHeader("Responsible");

    taskGrid.getColumnByKey("dueDate")
            .setHeader("Deadline");

    taskGrid.getColumnByKey("status")
            .setHeader("Status");

    taskGrid
         .addColumn(task -> {
           Button completeButton = new Button("Finish", new Icon(CHECK));

           completeButton.addClickListener(e -> completeTask(task));
           return completeButton;
         })
         .setHeader("Actions");

    mockedDatasourceUpdateList();
  }

  private void mockedDatasourceUpdateList() {

    var tasks = MockedDataSource.mockedProjectTaskList;

    if (! projectFilter.getValue()
                       .equals("All")) {
      tasks =
           tasks
                .stream()
                .filter(task ->
                             task.getProject()
                                 .equals(projectFilter.getValue()))
                .collect(Collectors.toList());
    }

    if (! statusFilter.getValue()
                      .equals("All")) {
      tasks =
           tasks
                .stream()
                .filter(task ->
                             task.getStatus()
                                 .equals(statusFilter.getValue()))
                .collect(Collectors.toList());
    }

    taskGrid.setItems(tasks);
  }

  private void completeTask(ProjectTask task) {
    // Lógica para marcar uma tarefa como concluída
    Notification.show("Concluded TAsk: " + task.getName(), 3000, Notification.Position.MIDDLE);
    task.setStatus("Concluded");
    taskGrid.getDataProvider()
            .refreshItem(task);
  }
}