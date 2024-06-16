package org.example.lab01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.lab01.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long>{
}
