package com.nellshark.controllers;

import com.nellshark.models.Project;
import com.nellshark.models.Team;
import com.nellshark.services.ProjectService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController extends AbstractGenericController<Project, Long> {

  private final ProjectService projectService;

  public ProjectController(ProjectService projectService) {
    super(projectService);
    this.projectService = projectService;
  }

  @GetMapping("/{id}/team")
  public ResponseEntity<List<Team>> getTeamByProjectId(@PathVariable("id") Long projectId) {
    List<Team> team = projectService.getTeamByProjectId(projectId);
    return ResponseEntity.ok(team);
  }
}
