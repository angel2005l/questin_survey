package com.xinhai.entity;

public class User {
	private int id;
	private String userAccount;
	private String userPass;
	private String userSalt;
	private String userName;

	public User() {
		super();
	}

	public User(String userAccount, String userPass, String userSalt, String userName) {
		super();
		this.userAccount = userAccount;
		this.userPass = userPass;
		this.userSalt = userSalt;
		this.userName = userName;
	}

	public User(int id, String userAccount, String userPass, String userSalt, String userName) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.userPass = userPass;
		this.userSalt = userSalt;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userAccount=" + userAccount + ", userPass=" + userPass + ", userSalt=" + userSalt
				+ ", userName=" + userName + "]";
	}

}
