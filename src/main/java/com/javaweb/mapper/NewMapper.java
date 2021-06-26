package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.NewModel;

public class NewMapper implements IRowMapper<NewModel>{

	@Override
	public NewModel mapRow(ResultSet rs) {
		try {
			NewModel newmd = new NewModel();
			newmd.setId(rs.getLong(1));
			newmd.setTitle(rs.getNString("title"));
			newmd.setContent(rs.getNString("content"));
			newmd.setCategoryId(rs.getLong("categoryid"));
			newmd.setThumbnail(rs.getNString("thumbnail"));
			newmd.setShortDescription(rs.getNString("shortdescription"));
			newmd.setCreatedDate(rs.getTimestamp("createddate"));
			newmd.setCreatedBy(rs.getNString("createdby"));
			if (rs.getTimestamp("modifieddate") != null) {
				newmd.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if (rs.getNString("modifiedby") != null) {
				newmd.setModifiedBy(rs.getNString("modifiedby"));
			}
			
			return newmd;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
