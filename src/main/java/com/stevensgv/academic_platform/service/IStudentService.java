package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    Student update(Long id, Student student);

    void deleteById(Long id);
}
