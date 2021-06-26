package com.javaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.javaweb.dao.IGenericDAO;
import com.javaweb.mapper.IRowMapper;

public class AbstractDAO<T> implements IGenericDAO<T> {
	
	private Connection conn;
	ResourceBundle configbundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
//		String url = "jdbc:mysql://localhost:3306/newjdbc28?useSSL=true";
//		String user = "root";
//		String pass = "123456";
		String url = configbundle.getString("url");
		String user = configbundle.getString("user");
		String pass = configbundle.getString("password");
		
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName(configbundle.getString("driverName"));
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to load MySQl Server driver", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return conn;
	}

	@Override
	public <T> List<T> query(String sql, IRowMapper<T> rowmapper, Object... param) {
		Connection connect = getConnection();
		List<T> list = new ArrayList<>();
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = connect.prepareStatement(sql);
			setParameter(pst, param);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rowmapper.mapRow(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	private void setParameter(PreparedStatement pst, Object... params) {	
		
		try {
			for(int i=0; i<params.length; i++) {
				Object parameter = params[i];
				int index = i+1;
				if(parameter instanceof Long) {
					pst.setLong(index, (Long)parameter);
				}
				else if(parameter instanceof String) {
					pst.setNString(index, (String)parameter);
				}
				else if(parameter instanceof Integer) {
					pst.setInt(index, (Integer)parameter);
				}
				else if(parameter instanceof Timestamp) {
					pst.setTimestamp(index, (Timestamp)parameter);
				}
				else if(parameter == null) {
					pst.setNull(index, Types.NULL);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public void update(String sql, Object... params) {
		Connection connect = getConnection();
		PreparedStatement pst;
//		ResultSet rs;
		try {
			connect.setAutoCommit(false);
			pst = connect.prepareStatement(sql);
			setParameter(pst, params);
			pst.executeUpdate();
			connect.commit();
		} catch (SQLException e) {
			if(connect!=null) {
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	@Override
	public Long insert(String sql, Object... params) {
		Connection connect = getConnection();
		PreparedStatement pst;
		ResultSet rs;
		Long id = null;
		try {
			connect.setAutoCommit(false);
			pst = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(pst, params);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getLong(1);
			}
			connect.commit();
			return id;
		} catch (SQLException e) {
			if(connect!=null) {
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int count(String sql, Object... params) {
		Connection connect = getConnection();
		PreparedStatement pst;
		ResultSet rs;
		try {
			int count = 0;
			pst = connect.prepareStatement(sql);
			setParameter(pst, params);
			rs = pst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
