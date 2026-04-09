package com.stevensgv.academic_platform.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String lastname;

    @Email
    @NotBlank
    @Size(max = 150)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String specialty;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"password", "roles", "accountNonExpired", "accountNonLocked", "credendialsNonExpired", "enabled"})
    private UserSec user;

    @OneToMany(mappedBy = "professor")
    @JsonIgnoreProperties({"professor", "students"})
    private Set<Course> courses = new HashSet<>();
}
