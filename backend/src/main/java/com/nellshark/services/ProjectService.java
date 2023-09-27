package com.nellshark.services;

import com.nellshark.models.Project;
import com.nellshark.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractGenericService<Project, Long> {

  public ProjectService(ProjectRepository projectRepository) {
    super(projectRepository);
  }
}
