package com.nellshark.repositories;

import com.nellshark.models.Project;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository extends AbstractGenericRepository<Project> {

  public ProjectRepository() {
    super(List.of());
  }
}
