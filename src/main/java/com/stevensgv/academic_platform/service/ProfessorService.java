package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.model.Professor;
import com.stevensgv.academic_platform.model.UserSec;
import com.stevensgv.academic_platform.repository.IProfessorRepository;
import com.stevensgv.academic_platform.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final IUserRepository userRepository;

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    public Professor save(Professor professor) {
        professor.setUser(resolveUser(professor.getUser().getId()));
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Long id, Professor professor) {
        Professor professorFound = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        professorFound.setFirstname(professor.getFirstname());
        professorFound.setLastname(professor.getLastname());
        professorFound.setEmail(professor.getEmail());
        professorFound.setSpecialty(professor.getSpecialty());
        professorFound.setUser(resolveUser(professor.getUser().getId()));

        return professorRepository.save(professorFound);
    }

    @Override
    public void deleteById(Long id) {
        if(!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor not found");
        }

        professorRepository.deleteById(id);
    }

    private UserSec resolveUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
