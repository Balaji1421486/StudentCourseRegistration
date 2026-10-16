package com.balaji.course_registration.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Instructor name is required")
    private String instructor;

    @Min(value = 1, message = "Credits must be at least 1")
    private int credits;

    public Course() {}
    public Course(String name, String instructor, int credits) {
        this.name = name; this.instructor = instructor; this.credits = credits;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
