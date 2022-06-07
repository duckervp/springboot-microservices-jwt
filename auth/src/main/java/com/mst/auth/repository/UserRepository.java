package com.mst.auth.repository;

import com.mst.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
