package com.nellshark.services;

import com.nellshark.models.Project;
import com.nellshark.models.Team;
import com.nellshark.repositories.ProjectRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractGenericService<Project, Long> {

  private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

  public ProjectService(ProjectRepository projectRepository) {
    super(projectRepository);
  }

  public List<Team> getTeamByProjectId(Long projectId) {
    logger.info("Getting team by project id: {}", projectId);
    return getEntityById(projectId).getTeam();
  }
}
