package com.nellshark.repositories;

import com.nellshark.models.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CustomGenericRepository<Course, Long> {

}
