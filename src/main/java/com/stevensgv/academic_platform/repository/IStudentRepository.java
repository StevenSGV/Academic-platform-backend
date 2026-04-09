package com.stevensgv.academic_platform.repository;

import com.stevensgv.academic_platform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {
}
