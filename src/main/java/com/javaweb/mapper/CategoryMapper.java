package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		try {
			CategoryModel catemd = new CategoryModel();
			catemd.setId(rs.getLong(1));
			catemd.setName(rs.getNString(2));
			catemd.setCode(rs.getNString(3));
			return catemd;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
