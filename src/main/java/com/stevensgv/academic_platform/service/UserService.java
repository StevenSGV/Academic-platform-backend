package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.model.Role;
import com.stevensgv.academic_platform.model.UserSec;
import com.stevensgv.academic_platform.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserSec> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserSec> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserSec> findByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public UserSec save(UserSec user) {
        user.setRoles(resolveRoles(user.getRoles()));
        user.setPassword(this.encryptPassword(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserSec update(UserSec userSec) {
        UserSec userFound = userRepository.findById(userSec.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userFound.setUsername(userSec.getUsername());
        userFound.setRoles(resolveRoles(userSec.getRoles()));
        userFound.setAccountNonExpired(userSec.isAccountNonExpired());
        userFound.setAccountNonLocked(userSec.isAccountNonLocked());
        userFound.setCredentialsNonExpired(userSec.isCredentialsNonExpired());
        userFound.setEnabled(userSec.isEnabled());

        if (userSec.getPassword() != null && !userSec.getPassword().isBlank()) {
            userFound.setPassword(encryptPassword(userSec.getPassword()));
        }

        return userRepository.save(userFound);
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Set<Role> resolveRoles(Set<Role> roles) {
        Set<Role> roleSet = roles.stream()
                .map(role ->
                        roleService.findById(role.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (roleSet.isEmpty()) {
            throw new ResourceNotFoundException("At least one valid role is required");
        }

        return roleSet;
    }
}
