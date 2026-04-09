package com.stevensgv.academic_platform.service;

import com.stevensgv.academic_platform.exception.ResourceNotFoundException;
import com.stevensgv.academic_platform.model.Permission;
import com.stevensgv.academic_platform.model.Role;
import com.stevensgv.academic_platform.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final IRoleRepository roleRepository;
    private final IPermissionService permissionService;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        role.setPermissions(resolvePermissions(role.getPermissions()));

        return roleRepository.save(role);
    }

    @Override
    public void deleteById(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Long id, Role role) {
        Role roleFound = this.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        roleFound.setRole(role.getRole());
        roleFound.setPermissions(resolvePermissions(role.getPermissions()));

        return roleRepository.save(roleFound);
    }

    private Set<Permission> resolvePermissions(Set<Permission> permissions) {
        Set<Permission> permissionSet = permissions.stream()
                .map(permission ->
                        permissionService.findById(permission.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (permissionSet.isEmpty()) {
            throw new ResourceNotFoundException("At least one valid permission is required");
        }

        return permissionSet;
    }
}
