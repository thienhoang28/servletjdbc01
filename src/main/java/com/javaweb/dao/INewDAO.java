package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.NewModel;
import com.javaweb.paging.IPageable;

public interface INewDAO extends IGenericDAO<NewModel>{
	NewModel findOne(Long id);
	List<NewModel> readNewByCategoryId(Long categoryId);
	Long addNew(NewModel newmodel);
	void updateNew(NewModel updatNew);
	void deleteNew(long id);
	List<NewModel> readAll(IPageable pageable);
	int getTotalItemDB();
}
