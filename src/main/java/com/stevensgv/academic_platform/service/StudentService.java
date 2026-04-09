package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.model.Course;
import com.stevensgv.academic_platform.model.Student;
import com.stevensgv.academic_platform.model.UserSec;
import com.stevensgv.academic_platform.repository.ICourseRepository;
import com.stevensgv.academic_platform.repository.IStudentRepository;
import com.stevensgv.academic_platform.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        student.setUser(resolveUser(student.getUser().getId()));
        student.setCourses(resolveCourses(student.getCourses()));
        return studentRepository.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        Student studentFound = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        studentFound.setFirstName(student.getFirstName());
        studentFound.setLastName(student.getLastName());
        studentFound.setCode(student.getCode());
        studentFound.setUser(resolveUser(student.getUser().getId()));
        studentFound.setCourses(resolveCourses(student.getCourses()));

        return studentRepository.save(studentFound);
    }

    @Override
    public void deleteById(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student with id: " + id + " not found");
        }

        studentRepository.deleteById(id);
    }

    private UserSec resolveUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Set<Course> resolveCourses(Set<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return new HashSet<>();
        }

        Set<Course> resolvedCourses = new HashSet<>();
        for (Course course : courses) {
            resolvedCourses.add(courseRepository.findById(course.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found")));
        }

        return resolvedCourses;
    }
}
