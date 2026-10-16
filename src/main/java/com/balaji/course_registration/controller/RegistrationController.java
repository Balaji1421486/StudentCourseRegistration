package com.balaji.course_registration.controller;

import com.balaji.course_registration.model.CourseRegistration;
import com.balaji.course_registration.repository.CourseRegistrationRepository;
import com.balaji.course_registration.repository.CourseRepository;
import com.balaji.course_registration.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {
    private final CourseRegistrationRepository registrations;
    private final StudentRepository students;
    private final CourseRepository courses;

    public RegistrationController(CourseRegistrationRepository registrations,
                                  StudentRepository students, CourseRepository courses) {
        this.registrations = registrations; this.students = students; this.courses = courses;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("registrations", registrations.findAll());
        model.addAttribute("students", students.findAll());
        model.addAttribute("courses", courses.findAll());
        return "registrations";
    }

    @PostMapping
    public String register(@RequestParam Long studentId, @RequestParam Long courseId,
                           RedirectAttributes redirect) {
        if (registrations.existsByStudentIdAndCourseId(studentId, courseId)) {
            redirect.addFlashAttribute("error", "This student is already registered for that course.");
            return "redirect:/registrations";
        }
        var student = students.findById(studentId);
        var course = courses.findById(courseId);
        if (student.isEmpty() || course.isEmpty()) {
            redirect.addFlashAttribute("error", "Please select a valid student and course.");
            return "redirect:/registrations";
        }
        CourseRegistration registration = new CourseRegistration();
        registration.setStudent(student.get());
        registration.setCourse(course.get());
        registrations.save(registration);
        redirect.addFlashAttribute("success", "Course registration completed.");
        return "redirect:/registrations";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        registrations.deleteById(id);
        redirect.addFlashAttribute("success", "Registration cancelled.");
        return "redirect:/registrations";
    }
}
