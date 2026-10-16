package com.balaji.course_registration.controller;

import com.balaji.course_registration.model.Course;
import com.balaji.course_registration.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repository;
    public CourseController(CourseRepository repository) { this.repository = repository; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("courses", repository.findAll());
        model.addAttribute("course", new Course());
        return "courses";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute Course course, BindingResult result,
                      Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("courses", repository.findAll());
            return "courses";
        }
        repository.save(course);
        redirect.addFlashAttribute("success", "Course added successfully.");
        return "redirect:/courses";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            repository.deleteById(id);
            redirect.addFlashAttribute("success", "Course deleted.");
        } catch (Exception ex) {
            redirect.addFlashAttribute("error", "This course may have registrations. Delete registrations first.");
        }
        return "redirect:/courses";
    }
}
