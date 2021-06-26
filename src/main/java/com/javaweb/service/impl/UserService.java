package com.javaweb.service.impl;

import javax.inject.Inject;

import com.javaweb.dao.IUserDAO;
import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;

public class UserService implements IUserService{
	
	@Inject
	private IUserDAO userdao;

	@Override
	public UserModel readAccount(String userName, String password, Integer status) {
		return userdao.readAccount(userName, password, status);
	}

}
