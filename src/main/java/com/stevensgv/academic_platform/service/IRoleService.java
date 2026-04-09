package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<Role> findAll();

    Optional<Role> findById(long id);

    Role save(Role role);

    void deleteById(long id);

    Role update(Long id, Role role);
}
