package com.certification.layouting.practice_project.utils;

import com.certification.layouting.practice_project.project.Project;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.now;

public class MockedDataSource {

   public static List<Project> projectList =
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