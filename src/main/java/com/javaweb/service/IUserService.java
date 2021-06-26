package com.javaweb.service;

import com.javaweb.model.UserModel;

public interface IUserService {
	UserModel readAccount(String userName, String password, Integer status);
}
