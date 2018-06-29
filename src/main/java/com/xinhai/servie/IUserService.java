package com.xinhai.servie;

import com.xinhai.entity.User;

public interface IUserService {
	
	public User login(String userAccount,String userPass);
}
