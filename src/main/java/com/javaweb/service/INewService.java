package com.javaweb.service;

import java.util.List;

import com.javaweb.model.NewModel;
import com.javaweb.paging.IPageable;

public interface INewService {
	List<NewModel> readNewByCategoryId(Long categoryId);
	NewModel addNew(NewModel newmodel);
	NewModel updateNew(NewModel updatNew);
	void deleteNew(long[] ids);
	List<NewModel> readAll(IPageable pageable);
	int getTotalItemDB();
	NewModel findOne(Long id); 
}
