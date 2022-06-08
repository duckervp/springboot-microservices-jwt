package com.mst.auth.service.impl;

import com.mst.auth.domain.entity.Role;
import com.mst.auth.repository.RoleRepository;
import com.mst.auth.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}

