package com.stevensgv.academic_platform.repository;

import com.stevensgv.academic_platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
