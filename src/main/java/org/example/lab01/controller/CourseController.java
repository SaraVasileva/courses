package org.example.lab01.controller;


import org.example.lab01.model.Course;
import org.example.lab01.model.CourseType;
import org.example.lab01.model.dto.CourseDto;
import org.example.lab01.service.CourseService;
import org.example.lab01.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

//    @GetMapping("/{id}")
//    public String findById(@PathVariable Long id, Model model) {
//        Course course = this.courseService.findById(id);
//        if (course != null) {
//            model.addAttribute("course", course);
//            return "course-details"; // You need to create a course-details.html view
//        } else {
//            return "redirect:/courses"; // Redirect to the course list if not found
//        }
//    }

    @GetMapping("/add")
    public String addCourseForm(Model model) {
        model.addAttribute("courseDto", new CourseDto());
        model.addAttribute("teachers", teacherService.listAll());
        model.addAttribute("types", CourseType.values());
        return "add-course";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute CourseDto courseDto) {
        if (teacherService.findById(courseDto.getTeacher()) != null) {
            this.courseService.create(courseDto.getName(), courseDto.getType(), courseDto.getTeacher(), courseDto.getNumStudents());
        }
        return "redirect:/courses";
    }

    @GetMapping
    public String getCourses(Model model) {
        List<Course> courses = this.courseService.listAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        if (courseService.findById(id) != null) {
            this.courseService.deleteById(id);
        }
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourseForm(@PathVariable Long id, Model model) {
        Course course = this.courseService.findById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("teachers", teacherService.listAll());
            model.addAttribute("types", CourseType.values());

            return "edit-course";
        }
        return "redirect:/courses";
    }

    @PostMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, @ModelAttribute CourseDto courseDto) {
        if (teacherService.findById(courseDto.getTeacher()) != null && courseService.findById(id) != null) {
            this.courseService.update(id, courseDto.getName(), courseDto.getType(), courseDto.getTeacher(), courseDto.getNumStudents());
        }
        return "redirect:/courses";
    }

    @GetMapping("/mark/{id}")
    public String markCourse(@PathVariable Long id) {
        if (courseService.findById(id) != null) {
            this.courseService.mark(id);
        }
        return "redirect:/courses";
    }

//    @GetMapping("/types")
//    public String getTypes(Model model) {
//        model.addAttribute("types", List.of(CourseType.values()));
//        return "course-types"; // You need to create a course-types.html view
//    }
}

