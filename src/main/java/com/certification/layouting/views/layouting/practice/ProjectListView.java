package com.certification.layouting.views.layouting.practice;

import com.certification.layouting.views.layouting.entity.Project;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

//@Route(value = "projects", layout = ProjectManagementView.class)
public class ProjectListView extends VerticalLayout {

  private Grid<Project> projectGrid;
  private TextField filterField;

  public ProjectListView() {

  }
}