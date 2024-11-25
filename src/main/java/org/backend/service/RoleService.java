package org.backend.service;


import lombok.RequiredArgsConstructor;
import org.backend.entity.Role;
import org.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role findByRoleName(String role){
        return repository.findByName(role);
    }
}