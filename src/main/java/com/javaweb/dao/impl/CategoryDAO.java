package com.javaweb.dao.impl;

import java.util.List;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.mapper.CategoryMapper;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.CategoryModel;
import com.javaweb.model.NewModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	@Override
	public List<CategoryModel> readAllCategory() {
		String sql = "Select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "Select * from category where id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
		
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "Select * from category where code = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
	}

}
