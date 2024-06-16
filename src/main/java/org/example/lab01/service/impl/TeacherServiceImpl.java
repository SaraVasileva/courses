package org.example.lab01.service.impl;

import org.example.lab01.model.Location;
import org.example.lab01.model.Teacher;
import org.example.lab01.model.exceptions.LocationNotFoundExc;
import org.example.lab01.model.exceptions.TeacherNotFoundExc;
import org.example.lab01.repository.LocationRepo;
import org.example.lab01.repository.TeacherRepo;
import org.example.lab01.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final LocationRepo locationRepo;

    public TeacherServiceImpl(TeacherRepo teacherRepo, LocationRepo locationRepo) {
        this.teacherRepo = teacherRepo;
        this.locationRepo = locationRepo;
    }


    @Override
    public List<Teacher> listAll() {
        return this.teacherRepo.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return this.teacherRepo.findById(id).orElseThrow(TeacherNotFoundExc::new);
    }

    @Override
    public void deleteById(Long id) {
        this.teacherRepo.deleteById(id);

    }

    @Override
    public Teacher create(String name, String surname, Long countryId) {
        Location location = this.locationRepo.findById(countryId).orElseThrow(LocationNotFoundExc::new);
        Teacher teacher = new Teacher(name,surname, location);
        return this.teacherRepo.save(teacher);
    }

    @Override
    public Teacher update(Long id, String name, String surname, Long countryId) {
        Teacher teacher = this.teacherRepo.findById(id).orElseThrow(TeacherNotFoundExc::new);
        Location location = this.locationRepo.findById(countryId).orElseThrow(LocationNotFoundExc::new);
        teacher.setLocation(location);
        teacher.setName(name);
        teacher.setSurname(surname);
        return this.teacherRepo.save(teacher);

    }
}
