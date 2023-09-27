package com.nellshark.controllers;

import com.nellshark.models.Course;
import com.nellshark.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
    Course course = courseService.getCourseById(id);
    return ResponseEntity.ok(course);
  }
}
