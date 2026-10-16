package com.balaji.course_registration.controller;

import com.balaji.course_registration.model.Student;
import com.balaji.course_registration.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repository;
    public StudentController(StudentRepository repository) { this.repository = repository; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", repository.findAll());
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute Student student, BindingResult result,
                      Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("students", repository.findAll());
            return "students";
        }
        if (repository.existsByEmail(student.getEmail())) {
            model.addAttribute("students", repository.findAll());
            model.addAttribute("duplicateEmail", "That email is already registered.");
            return "students";
        }
        repository.save(student);
        redirect.addFlashAttribute("success", "Student added successfully.");
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        repository.deleteById(id);
        redirect.addFlashAttribute("success", "Student deleted.");
        return "redirect:/students";
    }
}
