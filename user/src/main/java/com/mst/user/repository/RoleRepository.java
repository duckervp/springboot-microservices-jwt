package com.mst.user.repository;

import com.mst.user.domain.entity.Role;
import com.mst.user.domain.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
