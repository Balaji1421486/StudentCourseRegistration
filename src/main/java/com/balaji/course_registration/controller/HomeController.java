package com.balaji.course_registration.controller;

import com.balaji.course_registration.repository.CourseRegistrationRepository;
import com.balaji.course_registration.repository.CourseRepository;
import com.balaji.course_registration.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final StudentRepository students;
    private final CourseRepository courses;
    private final CourseRegistrationRepository registrations;

    public HomeController(StudentRepository students, CourseRepository courses,
                          CourseRegistrationRepository registrations) {
        this.students = students; this.courses = courses; this.registrations = registrations;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("studentCount", students.count());
        model.addAttribute("courseCount", courses.count());
        model.addAttribute("registrationCount", registrations.count());
        return "index";
    }
}
