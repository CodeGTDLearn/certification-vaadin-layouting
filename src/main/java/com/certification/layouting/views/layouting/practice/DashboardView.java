package com.certification.layouting.views.layouting.practice;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

//@Route(value = "dashboard", layout = ProjectManagementView.class)
public class DashboardView extends VerticalLayout implements HasUrlParameter<String> {

  private HorizontalLayout projectSummary;
  private VerticalLayout taskList;

  public DashboardView() {

  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    // Implementação para lidar com parâmetros de URL, se necessário
  }
}