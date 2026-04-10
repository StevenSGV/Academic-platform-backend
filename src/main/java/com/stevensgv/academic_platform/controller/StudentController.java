package com.stevensgv.academic_platform.controller;

import com.stevensgv.academic_platform.dto.StudentRequest;
import com.stevensgv.academic_platform.model.Course;
import com.stevensgv.academic_platform.model.Student;
import com.stevensgv.academic_platform.model.UserSec;
import com.stevensgv.academic_platform.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(toStudent(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.update(id, toStudent(request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Student toStudent(StudentRequest request) {
        UserSec user = new UserSec();
        user.setId(request.userId());

        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setCode(request.code());
        student.setUser(user);
        student.setCourses(toCourses(request.courseIds()));

        return student;
    }

    private Set<Course> toCourses(Set<Long> courseIds) {
        if (courseIds == null) {
            return Set.of();
        }

        return courseIds.stream()
                .map(courseId -> {
                    Course course = new Course();
                    course.setId(courseId);
                    return course;
                })
                .collect(Collectors.toSet());
    }
}
