package com.mst.user.service;

import com.mst.user.domain.entity.User;
import com.mst.user.domain.model.UserModel;

import java.util.List;

public interface IUserService {
	void delete(User user);

	List<UserModel> findAll();

	UserModel findById(Integer id);

	UserModel findByUsername(String username);

	Boolean existsByUsername(String username);

	List<String> findUserRoles(String username);
}
