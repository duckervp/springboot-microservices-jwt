package com.mst.auth.service;


import com.mst.auth.domain.entity.User;

import java.util.Optional;

public interface IUserService {

	User save(User user);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
}
