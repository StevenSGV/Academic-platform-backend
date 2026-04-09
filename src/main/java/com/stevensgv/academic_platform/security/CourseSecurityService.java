package com.stevensgv.academic_platform.security;

import com.stevensgv.academic_platform.model.Course;
import com.stevensgv.academic_platform.repository.ICourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseSecurityService {

    private final ICourseRepository courseRepository;

    public boolean isProfessorOfCourse(Long courseId, Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        String username = getUsername(authentication.getPrincipal());
        if (username == null) {
            return false;
        }

        return courseRepository.findById(courseId)
                .map(Course::getProfessor)
                .filter(professor -> professor.getUser() != null)
                .map(professor -> professor.getUser().getUsername())
                .filter(username::equals)
                .isPresent();
    }

    private String getUsername(Object principal) {
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }

        if (principal instanceof String username) {
            return username;
        }

        return null;
    }
}
