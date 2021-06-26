package com.javaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.javaweb.dao.INewDAO;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.NewModel;
import com.javaweb.paging.IPageable;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> readNewByCategoryId(Long categoryId) {
		String sql = "Select * from news where categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}
	
	@Override
	public Long addNew(NewModel newmodel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newmodel.getTitle(), newmodel.getContent(), 
				newmodel.getThumbnail(), newmodel.getShortDescription(), newmodel.getCategoryId(),
				newmodel.getCreatedDate(), newmodel.getCreatedBy());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "Select * from news where id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void updateNew(NewModel updatNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updatNew.getTitle(), updatNew.getThumbnail(), updatNew.getShortDescription(),
				updatNew.getContent(), updatNew.getCategoryId(), updatNew.getCreatedDate(), 
				updatNew.getCreatedBy(), updatNew.getModifiedDate(), 
				updatNew.getModifiedBy(), updatNew.getId());
		
	}

	@Override
	public void deleteNew(long id) {
		String sql = "Delete from news where id = ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> readAll(IPageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if (pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName()) && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageable.getSorter().getSortName()+" "+pageable.getSorter().getSortBy()+"");
		}
		if (pageable.getOffset() != null && pageable.getVisiblePage() != null) {
			sql.append(" LIMIT "+pageable.getOffset()+", "+pageable.getVisiblePage()+"");
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItemDB() {
		String sql = "Select count(*) from news";
		return count(sql);
	}

}
