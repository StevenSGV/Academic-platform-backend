package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.model.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {

    List<Professor> findAll();

    Optional<Professor> findById(Long id);

    Professor save(Professor professor);

    Professor update(Long id, Professor professor);

    void deleteById(Long id);
}
