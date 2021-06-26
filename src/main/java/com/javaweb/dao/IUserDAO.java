package com.javaweb.dao;

import com.javaweb.model.UserModel;

public interface IUserDAO extends IGenericDAO<UserModel>{
	UserModel readAccount(String userName, String password, Integer status);
}
