package com.certification.layouting.practice_project.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Project {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}