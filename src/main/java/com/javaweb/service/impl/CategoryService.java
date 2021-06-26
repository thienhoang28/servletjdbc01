package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.model.CategoryModel;
import com.javaweb.service.ICategoryService;

public class CategoryService implements ICategoryService{

//	private ICategoryDAO catedao;
//	
//	public CategoryService() {
//		catedao = new CategoryDAO();
//	}
	
	@Inject
	private ICategoryDAO catedao;
	
	@Override
	public List<CategoryModel> readAllCategory() {
		return catedao.readAllCategory();
	}

}
