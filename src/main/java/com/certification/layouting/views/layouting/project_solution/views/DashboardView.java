package com.certification.layouting.views.layouting.project_solution.views;

import com.certification.layouting.views.layouting.ProjectManagementMainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = ProjectManagementMainView.class)
public class DashboardView extends VerticalLayout implements HasUrlParameter<String> {

  private HorizontalLayout projectSummary;
  private VerticalLayout taskList;

  public DashboardView() {

    setSizeFull();
    setPadding(false);
    setSpacing(false);

    add(
         createHeader(),
         createMainContent(),
         createFooter()
    );
  }

  private HorizontalLayout createHeader() {

    HorizontalLayout header = new HorizontalLayout();
    header.setWidthFull();
    header.setHeight("80px");
    header.setClassName("dashboard-header");
    header.setAlignItems(Alignment.CENTER);
    header.setPadding(true);

    H2 title = new H2("Dashboard");
    title.getStyle()
         .set("margin", "0");

    Button newProjectBtn = new Button("New Project", new Icon(VaadinIcon.PLUS));
    newProjectBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    header.add(title, newProjectBtn);
    header.setJustifyContentMode(JustifyContentMode.BETWEEN);

    return header;
  }

  private HorizontalLayout createMainContent() {

    HorizontalLayout mainContent = new HorizontalLayout();
    mainContent.setSizeFull();
    mainContent.setPadding(true);
    mainContent.setSpacing(true);

    projectSummary = new HorizontalLayout();
    projectSummary.setWidthFull();
    projectSummary.setHeight("200px");
    projectSummary.setClassName("project-summary");

    taskList = new VerticalLayout();
    taskList.setHeightFull();
    taskList.setWidth("300px");
    taskList.setClassName("task-list");

    mainContent.add(createProjectSummary(), createTaskList());
    return mainContent;
  }

  private VerticalLayout createProjectSummary() {

    VerticalLayout summaryLayout = new VerticalLayout();
    summaryLayout.setWidthFull();
    summaryLayout.setSpacing(false);
    summaryLayout.setPadding(true);
    summaryLayout.setClassName("summary-card");

    H3 summaryTitle = new H3("Projects Summary");
    summaryLayout.add(summaryTitle);

    HorizontalLayout stats = new HorizontalLayout();
    stats.setWidthFull();
    stats.setJustifyContentMode(JustifyContentMode.BETWEEN);

    stats.add(
         createStatItem("Active Projects", "10"),
         createStatItem("Ongoing Projects", "25"),
         createStatItem("Concluded Projects", "5")
    );

    summaryLayout.add(stats);
    return summaryLayout;
  }

  private VerticalLayout createStatItem(String label, String value) {

    VerticalLayout statItem = new VerticalLayout();
    statItem.setSpacing(false);
    statItem.setPadding(false);
    statItem.setAlignItems(Alignment.CENTER);

    H2 statValue = new H2(value);
    Span statLabel = new Span(label);

    statItem.add(statValue, statLabel);
    return statItem;
  }

  private VerticalLayout createTaskList() {

    VerticalLayout taskListLayout = new VerticalLayout();
    taskListLayout.setHeightFull();
    taskListLayout.setClassName("task-list-card");

    H3 taskListTitle = new H3("Recente Tasks");
    taskListLayout.add(taskListTitle);

    for (int i = 1; i <= 5; i++) {
      taskListLayout.add(createTaskItem("Project " + i, "Task " + i));
    }

    return taskListLayout;
  }

  private HorizontalLayout createTaskItem(String taskName, String projectName) {

    HorizontalLayout taskItem = new HorizontalLayout();
    taskItem.setWidthFull();
    taskItem.setAlignItems(Alignment.CENTER);

    Checkbox taskCheckbox = new Checkbox(taskName);
    Span projectLabel = new Span(projectName);
    projectLabel.getStyle()
                .set("margin-left", "auto");

    taskItem.add(taskCheckbox, projectLabel);
    return taskItem;
  }

  private HorizontalLayout createFooter() {

    HorizontalLayout footer = new HorizontalLayout();
    footer.setWidthFull();
    footer.setHeight("50px");
    footer.setClassName("dashboard-footer");
    footer.setAlignItems(Alignment.CENTER);
    footer.setJustifyContentMode(JustifyContentMode.CENTER);

    footer.add(new Span("© 2024 Project Management Software"));

    return footer;
  }

  @Override
  public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    // Implementação para lidar com parâmetros de URL, se necessário
  }
}