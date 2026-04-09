package com.stevensgv.academic_platform.repository;

import com.stevensgv.academic_platform.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
}
