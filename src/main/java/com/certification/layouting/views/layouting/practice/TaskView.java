package com.certification.layouting.views.layouting.practice;

import com.certification.layouting.views.layouting.entity.ProjectTask;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

//@Route(value = "tasks", layout = ProjectManagementView.class)
public class TaskView extends VerticalLayout {

  private Grid<ProjectTask> taskGrid;
  private ComboBox<String> projectFilter;
  private ComboBox<String> statusFilter;

  public TaskView() {

  }
}