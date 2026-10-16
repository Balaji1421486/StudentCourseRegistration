package com.balaji.course_registration.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "course_registrations",
       uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate registeredOn;

    @PrePersist
    public void setRegistrationDate() {
        if (registeredOn == null) registeredOn = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public LocalDate getRegisteredOn() { return registeredOn; }
    public void setRegisteredOn(LocalDate registeredOn) { this.registeredOn = registeredOn; }
}
