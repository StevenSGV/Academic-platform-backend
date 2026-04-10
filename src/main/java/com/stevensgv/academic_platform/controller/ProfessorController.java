package com.stevensgv.academic_platform.controller;

import com.stevensgv.academic_platform.dto.ProfessorRequest;
import com.stevensgv.academic_platform.model.Professor;
import com.stevensgv.academic_platform.model.UserSec;
import com.stevensgv.academic_platform.service.IProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/professors")
public class ProfessorController {

    private final IProfessorService professorService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok().body(professorService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return professorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> (ResponseEntity.notFound().build()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Professor> createProfessor(@Valid @RequestBody ProfessorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(toProfessor(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorRequest request) {
        return ResponseEntity.ok(professorService.update(id, toProfessor(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Professor toProfessor(ProfessorRequest request) {
        UserSec user = new UserSec();
        user.setId(request.userId());

        Professor professor = new Professor();
        professor.setFirstname(request.firstname());
        professor.setLastname(request.lastname());
        professor.setEmail(request.email());
        professor.setSpecialty(request.specialty());
        professor.setUser(user);

        return professor;
    }
}
