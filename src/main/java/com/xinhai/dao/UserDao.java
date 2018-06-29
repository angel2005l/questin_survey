package com.xinhai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xinhai.entity.User;
import com.xinhai.util.SqlUtil;

public class UserDao {

	@SuppressWarnings("finally")
	public User selUserByUserAccount(String userAccount) {
		Connection conn = SqlUtil.getConn();
		User user = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from user where user_account = ?");
			ps.setString(1, userAccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
			return user;
		}
	}
}
