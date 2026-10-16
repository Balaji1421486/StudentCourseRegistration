package com.balaji.course_registration.repository;

import com.balaji.course_registration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
