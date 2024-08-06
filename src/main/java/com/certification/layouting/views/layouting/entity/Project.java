package com.certification.layouting.views.layouting.entity;

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