package com.javaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.ICategoryDAO;
import com.javaweb.dao.INewDAO;
import com.javaweb.model.CategoryModel;
import com.javaweb.model.NewModel;
import com.javaweb.paging.IPageable;
import com.javaweb.service.INewService;

public class NewService implements INewService{
	
	@Inject
	private INewDAO newdao;
	
	@Inject
	private ICategoryDAO categoryDAO;

	@Override
	public List<NewModel> readNewByCategoryId(Long categoryId) {
		return newdao.readNewByCategoryId(categoryId);
	}

	@Override
	public NewModel addNew(NewModel newmodel) {
		newmodel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category = categoryDAO.findOneByCode(newmodel.getCategoryCode());
		newmodel.setCategoryId(category.getId());
		Long newId = newdao.addNew(newmodel);
		return newdao.findOne(newId);
	}

	@Override
	public NewModel updateNew(NewModel updatNew) {
		NewModel oldNew = newdao.findOne(updatNew.getId());
		updatNew.setCreatedDate(oldNew.getCreatedDate());
		updatNew.setCreatedBy(oldNew.getCreatedBy());
		updatNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category = categoryDAO.findOneByCode(updatNew.getCategoryCode());
		updatNew.setCategoryId(category.getId());
		newdao.updateNew(updatNew);
		return newdao.findOne(updatNew.getId());
	}

	@Override
	public void deleteNew(long[] ids) {
		for(long id: ids) {
			newdao.deleteNew(id);
		}
	}

	@Override
	public List<NewModel> readAll(IPageable pageable) {
		return newdao.readAll(pageable);
	}

	@Override
	public int getTotalItemDB() {
		return newdao.getTotalItemDB();
	}

	@Override
	public NewModel findOne(Long id) {
		NewModel newModel = newdao.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
	}

}
