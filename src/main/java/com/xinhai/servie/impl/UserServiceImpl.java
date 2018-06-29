package com.xinhai.servie.impl;

import com.xinhai.dao.UserDao;
import com.xinhai.entity.User;
import com.xinhai.servie.IUserService;
import com.xinhai.util.MD5Util;

public class UserServiceImpl implements IUserService {

	private UserDao dao = new UserDao();

	@Override
	public User login(String userAccount, String userPass) {
		User user = dao.selUserByUserAccount(userAccount);
		if (null != user) {
			return MD5Util.check(userPass, user.getUserSalt(), user.getUserPass()) ? user : null;
		}
		return null;
	}

}
