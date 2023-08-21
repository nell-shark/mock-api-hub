package com.nellshark.controllers;

import com.nellshark.models.Project;
import com.nellshark.services.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController extends AbstractGenericController<Project> {

  public ProjectController(ProjectService service) {
    super(service);
  }
}
