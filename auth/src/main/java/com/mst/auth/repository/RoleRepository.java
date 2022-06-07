package com.mst.auth.repository;

import com.mst.auth.domain.entity.Role;
import com.mst.auth.domain.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
