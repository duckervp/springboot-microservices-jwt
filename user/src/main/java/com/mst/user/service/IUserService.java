package com.mst.user.service;

import com.mst.user.domain.entity.User;
import com.mst.user.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
	Integer save(User user);
	void update(User user);
	void delete(User user);

	List<UserModel> findAll();

	UserModel findById(Integer id);

	Optional<User> findByUsername(String username);

	UserModel findUserModelByUsername(String username);

	Boolean existsByUsername(String username);
}
