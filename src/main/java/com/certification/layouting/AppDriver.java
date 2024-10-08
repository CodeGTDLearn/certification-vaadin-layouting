package com.certification.layouting;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Theme(value = "layout")
public class AppDriver extends SpringBootServletInitializer implements AppShellConfigurator {

  public static void main(String[] args) {

    SpringApplication.run(AppDriver.class, args);
  }

}