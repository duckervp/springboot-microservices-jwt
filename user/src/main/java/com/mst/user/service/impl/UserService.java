package com.mst.user.service.impl;

import com.mst.user.client.MajorClient;
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

	private final MajorClient majorClient;

	private final ModelMapper modelMapper;

	@Override
	public void delete(User user) {
		userRepository.deleteById(user.getId());
	}

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll().stream()
				.map(user -> modelMapper.map(user, UserModel.class))
				.peek(userModel -> userModel.setMajor(majorClient.findById(userModel.getId())))
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
			return mapUserModelFrom(user);
		}
		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public List<String> findUserRoles(String username) {
		UserModel user = findByUsername(username);
		return user.getRoles().stream()
				.map(role -> role.getName().name())
				.collect(Collectors.toList());
	}

	public UserModel mapUserModelFrom(User user) {
		UserModel userModel = modelMapper.map(user, UserModel.class);
		userModel.setMajor(majorClient.findById(userModel.getId()));
		return userModel;
	}
}
