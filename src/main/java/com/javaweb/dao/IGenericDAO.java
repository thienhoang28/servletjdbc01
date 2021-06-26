package com.javaweb.dao;

import java.util.List;

import com.javaweb.mapper.IRowMapper;

public interface IGenericDAO<T> {
	<T> List<T> query(String sql, IRowMapper<T> rowmapper, Object... param);
	void update(String sql, Object... params);
	Long insert(String sql, Object... params);
	int count(String sql, Object... params);
}
