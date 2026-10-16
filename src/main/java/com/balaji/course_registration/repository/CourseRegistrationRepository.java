package com.balaji.course_registration.repository;

import com.balaji.course_registration.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
