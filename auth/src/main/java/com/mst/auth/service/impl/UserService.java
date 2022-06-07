package com.mst.auth.service.impl;

import com.mst.auth.domain.entity.User;
import com.mst.auth.repository.UserRepository;
import com.mst.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	private final UserRepository userRepository;
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
}
