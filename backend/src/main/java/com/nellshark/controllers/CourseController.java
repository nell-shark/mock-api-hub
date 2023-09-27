package com.nellshark.controllers;

import com.nellshark.models.Course;
import com.nellshark.services.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController extends AbstractGenericController<Course, Long> {

  public CourseController(CourseService courseService) {
    super(courseService);
  }
}
