package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.TreePath;

import com.javaweb.model.NewModel;
import com.javaweb.model.RoleModel;
import com.javaweb.model.UserModel;

public class UserMapper implements IRowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel usermd = new UserModel();
			usermd.setId(rs.getLong(1));
			usermd.setUserName(rs.getNString("username"));
			usermd.setUserName(rs.getNString("username"));
			usermd.setFullName(rs.getNString("fullname"));
			usermd.setPassword(rs.getNString("password"));
			usermd.setStatus(rs.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				usermd.setRole(role);
			} catch (Exception e1) {
				System.out.print(e1.getMessage());
			}
			
			return usermd;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
