package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICouseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course save(Course course);

    Course update(Long id, Course course);

    void deleteById(Long id);
}
