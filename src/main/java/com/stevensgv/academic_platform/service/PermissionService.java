package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.model.Permission;
import com.stevensgv.academic_platform.repository.IPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {

    private final IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {
        if (permission.getId() == null || !permissionRepository.existsById(permission.getId())) {
            throw new ResourceNotFoundException("Permission with id " + permission.getId() + " does not exist");
        }

        return permissionRepository.save(permission);
    }
}
