package com.nellshark.services;

import com.nellshark.exceptions.CourseNotFoundException;
import com.nellshark.models.Course;
import com.nellshark.repositories.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

  private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course getCourseById(Long id) {
    return courseRepository
        .findById(id)
        .orElseThrow(
            () -> new CourseNotFoundException("Course with id %s not found".formatted(id)));
  }
}
