package com.certification.layouting.views.layouting.project_solution.views;

import com.certification.layouting.views.layouting.ProjectManagementMainView;
import com.certification.layouting.views.layouting.project_solution.entity.ProjectTask;
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

@Route(value = "tasks", layout = ProjectManagementMainView.class)
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

    projectFilter = new ComboBox<>("Projeto");
    projectFilter.setItems("Todos", "Projeto A", "Projeto B", "Projeto C");
    projectFilter.setValue("Todos");
    projectFilter.addValueChangeListener(e -> updateList());

    statusFilter = new ComboBox<>("Status");
    statusFilter.setItems("Todos", "Pendente", "Em andamento", "Concluído");
    statusFilter.setValue("Todos");
    statusFilter.addValueChangeListener(e -> updateList());
  }

  private void createTaskGrid() {

    taskGrid = new Grid<>(ProjectTask.class);

    taskGrid.setColumns("name", "project", "assignedTo", "dueDate", "status");

    taskGrid
         .getColumnByKey("name")
         .setHeader("Tarefa");

    taskGrid.getColumnByKey("project")
            .setHeader("Projeto");

    taskGrid.getColumnByKey("assignedTo")
            .setHeader("Responsável");

    taskGrid.getColumnByKey("dueDate")
            .setHeader("Prazo");

    taskGrid.getColumnByKey("status")
            .setHeader("Status");

    taskGrid
         .addColumn(task -> {
           Button completeButton = new Button("Concluir", new Icon(CHECK));

           completeButton.addClickListener(e -> completeTask(task));
           return completeButton;
         })
         .setHeader("Ações");

    updateList();
  }

  private void updateList() {
    // Aqui você normalmente buscaria os dados do seu backend
    // Para este exemplo, vamos criar algumas tarefas de exemplo
    final ProjectTask mockProjectTask1 =
         new ProjectTask("Tarefa 1",
                         "Projeto A",
                         "João",
                         LocalDate.now()
                                  .plusDays(7),
                         "Pendente"
         );

    final ProjectTask mockProjectTask2 =
         new ProjectTask(
              "Tarefa 2",
              "Projeto B",
              "Maria",
              LocalDate.now()
                       .plusDays(
                            3),
              "Em andamento"
         );


    List<ProjectTask> tasks = Arrays.asList(mockProjectTask1, mockProjectTask2);


    if (! projectFilter.getValue()
                       .equals("Todos")) {
      tasks = tasks.stream()
                   .filter(task ->
                                task.getProject().equals(projectFilter.getValue()))
                   .collect(Collectors.toList());
    }

    if (! statusFilter.getValue()
                      .equals("Todos")) {
      tasks = tasks.stream()
                   .filter(task ->
                                task.getStatus().equals(statusFilter.getValue()))
                   .collect(Collectors.toList());
    }

    taskGrid.setItems(tasks);
  }

  private void completeTask(ProjectTask task) {
    // Lógica para marcar uma tarefa como concluída
    Notification.show("Tarefa concluída: " + task.getName(), 3000, Notification.Position.MIDDLE);
    task.setStatus("Concluído");
    taskGrid.getDataProvider()
            .refreshItem(task);
  }
}