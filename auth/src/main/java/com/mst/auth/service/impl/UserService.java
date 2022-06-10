package com.mst.auth.service.impl;

import com.mst.auth.domain.entity.User;
import com.mst.auth.repository.UserRepository;
import com.mst.auth.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<String> findUserRoles(String username) {
		User user = findByUsername(username).orElse(null);
		if (Objects.nonNull(user)) {
			return user.getRoles().stream()
					.map(role -> role.getName().name())
					.collect(Collectors.toList());
		}
		return null;
	}
}
