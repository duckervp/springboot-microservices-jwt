package com.mst.user.service.impl;

import com.mst.user.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mst.user.domain.entity.User;
import com.mst.user.repository.UserRepository;
import com.mst.user.service.IUserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
	private final UserRepository userRepository;

	private final ModelMapper modelMapper;

	@Override
	public Integer save(User user) {
		return userRepository.save(user).getId();
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UserModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserModel findById(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			return modelMapper.map(user, UserModel.class);
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		User user = userRepository.findByUsername(username).orElse(null);
		if (Objects.nonNull(user)) {
			return modelMapper.map(user, UserModel.class);
		}
		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
}
