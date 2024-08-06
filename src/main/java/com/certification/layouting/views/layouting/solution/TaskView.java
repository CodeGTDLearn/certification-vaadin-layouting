package com.certification.layouting.views.layouting.solution;

import com.certification.layouting.views.layouting.ProjectManagementView;
import com.certification.layouting.views.layouting.entity.ProjectTask;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.vaadin.flow.component.icon.VaadinIcon.CHECK;

@Route(value = "tasks", layout = ProjectManagementView.class)
public class TaskView extends VerticalLayout {

  private Grid<ProjectTask> taskGrid;
  private ComboBox<String> projectFilter;
  private ComboBox<String> statusFilter;

  public TaskView() {

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

  // Aqui você normalmente buscaria os dados do seu backend
  // Para este exemplo, vamos criar algumas tarefas de exemplo
  private void mockedDatasourceUpdateList() {

    final ProjectTask mockProjectTask1 =
         new ProjectTask("Task 1",
                         "Project A",
                         "Jonh",
                         LocalDate.now()
                                  .plusDays(7),
                         "On going"
         );

    final ProjectTask mockProjectTask2 =
         new ProjectTask(
              "Task 2",
              "Project B",
              "Mary",
              LocalDate.now()
                       .plusDays(
                            3),
              "On going"
         );


    List<ProjectTask> tasks = Arrays.asList(mockProjectTask1, mockProjectTask2);


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