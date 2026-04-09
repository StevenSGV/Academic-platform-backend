package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.exception.InvalidRequestException;
import com.stevensgv.academic_platform.model.Course;
import com.stevensgv.academic_platform.model.Professor;
import com.stevensgv.academic_platform.model.Student;
import com.stevensgv.academic_platform.repository.ICourseRepository;
import com.stevensgv.academic_platform.repository.IProfessorRepository;
import com.stevensgv.academic_platform.repository.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService implements ICouseService {

    private final ICourseRepository courseRepository;
    private final IProfessorRepository professorRepository;
    private final IStudentRepository studentRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        course.setProfessor(resolveProfessor(course.getProfessor().getId()));
        course.setStudents(resolveStudents(course.getStudents()));
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course, Authentication authentication) {
        Course courseFound = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courseFound.setTitle(course.getTitle());
        courseFound.setDescription(course.getDescription());
        if (isProfessor(authentication) && !isAdmin(authentication)) {
            validateProfessorIsNotChanged(courseFound, course);
        } else {
            courseFound.setProfessor(resolveProfessor(course.getProfessor().getId()));
        }
        courseFound.setStudents(resolveStudents(course.getStudents()));

        return courseRepository.save(courseFound);
    }

    @Override
    public void deleteById(Long id) {
        if(!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }

        courseRepository.deleteById(id);
    }

    private Professor resolveProfessor(Long professorId) {
        return professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));
    }

    private Set<Student> resolveStudents(Set<Student> students) {
        if (students == null || students.isEmpty()) {
            return new HashSet<>();
        }

        Set<Student> resolvedStudents = new HashSet<>();
        for (Student student : students) {
            resolvedStudents.add(studentRepository.findById(student.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found")));
        }

        return resolvedStudents;
    }

    private void validateProfessorIsNotChanged(Course courseFound, Course course) {
        if (course.getProfessor() == null || course.getProfessor().getId() == null) {
            return;
        }

        if (!courseFound.getProfessor().getId().equals(course.getProfessor().getId())) {
            throw new InvalidRequestException("Professors cannot reassign courses to another professor");
        }
    }

    private boolean isProfessor(Authentication authentication) {
        return hasAuthority(authentication, "ROLE_PROFESSOR");
    }

    private boolean isAdmin(Authentication authentication) {
        return hasAuthority(authentication, "ROLE_ADMIN");
    }

    private boolean hasAuthority(Authentication authentication, String authority) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
    }
}
