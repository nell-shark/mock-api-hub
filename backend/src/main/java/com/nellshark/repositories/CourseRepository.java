package com.nellshark.repositories;

import com.nellshark.models.Course;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends AbstractGenericRepository<Course> {

  public CourseRepository() {
    super(List.of(new Course(1L)));
  }
}
