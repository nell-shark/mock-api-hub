package com.nellshark.services;

import com.nellshark.exceptions.ProjectNotFoundException;
import com.nellshark.models.Project;
import com.nellshark.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Project getProjectById(Long id) {
    return projectRepository
        .findById(id)
        .orElseThrow(
            () -> new ProjectNotFoundException("Project with id %s not found".formatted(id)));
  }
}
