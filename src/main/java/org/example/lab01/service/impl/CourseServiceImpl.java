package org.example.lab01.service.impl;

import org.example.lab01.model.Course;
import org.example.lab01.model.Teacher;
import org.example.lab01.model.CourseType;
import org.example.lab01.model.events.CourseApplyingEvent;
import org.example.lab01.model.exceptions.CourseNotFoundExc;
import org.example.lab01.model.exceptions.TeacherNotFoundExc;
import org.example.lab01.repository.CourseRepo;
import org.example.lab01.repository.TeacherRepo;
import org.example.lab01.service.CourseService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final TeacherRepo teacherRepo;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CourseServiceImpl(CourseRepo courseRepo, TeacherRepo teacherRepo, ApplicationEventPublisher applicationEventPublisher) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;

        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Course> listAll() {
        return this.courseRepo.findAll();
    }

    @Override
    public Course findById(Long id) {
        return this.courseRepo.findById(id).orElseThrow(CourseNotFoundExc::new);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepo.deleteById(id);

    }

    @Override
    public Course create(String name, CourseType type, Long host, Integer numRooms) {
        Teacher teacher1 = this.teacherRepo.findById(host).orElseThrow(TeacherNotFoundExc::new);
        Course acc = new Course(name, type, teacher1, numRooms);
        return this.courseRepo.save(acc);
    }

    @Override
    public Course update(Long id, String name, CourseType type, Long host, Integer numStudents) {
        Teacher teacher1 = this.teacherRepo.findById(host).orElseThrow(TeacherNotFoundExc::new);
        Course acc = this.courseRepo.findById(id).orElseThrow(CourseNotFoundExc::new);
        acc.setName(name);
        acc.setType(type);
        acc.setNumStudents(numStudents);
        acc.setTeacher(teacher1);
        return this.courseRepo.save(acc);

    }

    @Override
    public void mark(Long id) {
        Course acc = this.courseRepo.findById(id).orElseThrow(CourseNotFoundExc::new);

            if (acc.getNumStudents() == 0) {
                this.applicationEventPublisher.publishEvent(new CourseApplyingEvent(acc));
            }
            else {
                acc.setNumStudents(acc.getNumStudents() - 1);
                courseRepo.save(acc);
            }


    }

    @Override
    public void book() {
        System.out.println("THE COURSE IS FULL!");
    }


}
