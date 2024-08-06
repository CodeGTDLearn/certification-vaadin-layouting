package com.certification.layouting.views.layouting.project_solution.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ProjectTask {

  private String name;
  private String project;
  private String assignedTo;
  private LocalDate dueDate;
  private String status;
}