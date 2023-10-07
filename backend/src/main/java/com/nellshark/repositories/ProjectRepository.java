package com.nellshark.repositories;

import com.nellshark.models.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CustomGenericRepository<Project, Long> {

}
