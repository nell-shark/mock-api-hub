package com.nellshark.controllers;

import com.nellshark.models.Project;
import com.nellshark.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
    Project project = projectService.getProjectById(id);
    return ResponseEntity.ok(project);
  }
}
