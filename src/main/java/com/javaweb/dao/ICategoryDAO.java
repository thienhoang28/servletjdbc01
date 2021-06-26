package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel>{
	List<CategoryModel> readAllCategory();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
}
