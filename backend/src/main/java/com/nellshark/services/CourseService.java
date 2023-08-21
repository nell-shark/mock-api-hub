package com.nellshark.services;

import com.nellshark.models.Course;
import com.nellshark.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends AbstractGenericService<Course> {

  public CourseService(CourseRepository courseRepository) {
    super(courseRepository);
  }
}
