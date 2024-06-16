
package org.example.lab01.controller;

import org.example.lab01.model.Teacher;
import org.example.lab01.service.LocationService;
import org.example.lab01.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final LocationService locationService;

    public TeacherController(TeacherService teacherService, LocationService locationService) {
        this.teacherService = teacherService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getTeachers(Model model) {
        List<Teacher> teachers = this.teacherService.listAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }


    @PostMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        if (teacherService.findById(id) != null) {
            this.teacherService.deleteById(id);
        }
        return "redirect:/teachers";
    }
}
