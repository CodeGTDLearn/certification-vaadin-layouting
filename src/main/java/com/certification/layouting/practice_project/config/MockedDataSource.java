package com.certification.layouting.practice_project.config;

import com.certification.layouting.practice_project.project.Project;
import com.certification.layouting.practice_project.projecttask.ProjectTask;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.now;

public class MockedDataSource {

  public static List<ProjectTask> mockedProjectTaskList =
       Arrays.asList(
            new ProjectTask("Task 1",
                            "Project A",
                            "Jonh",
                            LocalDate.now()
                                     .plusDays(7),
                            "On going"
            ),
            new ProjectTask(
                 "Task 2",
                 "Project B",
                 "Mary",
                 LocalDate.now()
                          .plusDays(
                               3),
                 "On going"
            )

       );


  public static List<Project> mockedProjectList =
       Arrays.asList(
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
}