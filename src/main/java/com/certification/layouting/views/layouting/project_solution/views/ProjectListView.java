package com.certification.layouting.views.layouting.project_solution.views;

import com.certification.layouting.views.layouting.ProjectManagementMainView;
import com.certification.layouting.views.layouting.project_solution.entity.Project;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "projects", layout = ProjectManagementMainView.class)
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
        filterField.setPlaceholder("Filtrar por nome...");
        filterField.setClearButtonVisible(true);
        filterField.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Adicionar Projeto", new Icon(VaadinIcon.PLUS));
        addButton.addClickListener(e -> addProject());

        toolbar.add(filterField, addButton);
        add(toolbar);
    }

    private void createProjectGrid() {
        projectGrid = new Grid<>(Project.class);
        projectGrid.setColumns("name", "startDate", "endDate", "status");
        projectGrid.getColumnByKey("name").setHeader("Nome do Projeto");
        projectGrid.getColumnByKey("startDate").setHeader("Data de Início");
        projectGrid.getColumnByKey("endDate").setHeader("Data de Término");
        projectGrid.getColumnByKey("status").setHeader("Status");

        projectGrid.addColumn(project -> {
            Button editButton = new Button("Editar", new Icon(VaadinIcon.EDIT));
            editButton.addClickListener(e -> editProject(project));
            return editButton;
        }).setHeader("Ações");

        updateList();
    }

    private void updateList() {
        // Aqui você normalmente buscaria os dados do seu backend
        // Para este exemplo, vamos criar alguns projetos de exemplo
        List<Project> projects = Arrays.asList(
            new Project("Projeto A", LocalDate.now(), LocalDate.now().plusMonths(3), "Em andamento"),
            new Project("Projeto B", LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(2), "Em andamento"),
            new Project("Projeto C", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(6), "Planejado")
        );

        if (filterField.getValue() != null && !filterField.getValue().isEmpty()) {
            projects = projects.stream()
                .filter(project -> project.getName().toLowerCase().contains(filterField.getValue().toLowerCase()))
                .collect(Collectors.toList());
        }

        projectGrid.setItems(projects);
    }

    private void addProject() {
        // Lógica para adicionar um novo projeto
        Notification.show("Funcionalidade de adicionar projeto não implementada", 3000, Notification.Position.MIDDLE);
    }

    private void editProject(Project project) {
        // Lógica para editar um projeto existente
        Notification.show("Editando projeto: " + project.getName(), 3000, Notification.Position.MIDDLE);
    }
}



// Classes de modelo