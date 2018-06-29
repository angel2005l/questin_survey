<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0058)http://food1.xinhaiip.cn/web/xinhai/canteen/html/Login.jsp -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript" src="/emp/js/jquery-3.2.1.js"></script>
<link rel="stylesheet prefetch"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/emp/css/login.css">
</head>

<body>
	<div class="wrapper">
		<form class="form-signin">
			<img src="/emp/img/xinhai.png" style="margin-left: -35px" alt="">
			<h2 class="form-signin-heading">新海问卷管理登录</h2>
			<input type="text" class="form-control" name="user_name"
				id="user_account" placeholder="用户名" required="" autofocus=""> <input
				type="password" class="form-control" name="user_pwd" id="user_pwd"
				placeholder="密码" required="">
			<button class="btn btn-lg btn-primary btn-block" id="submit"
				type="button" onclick="login()">登录</button>
		</form>
	</div>
<script type="text/javascript" src="/emp/js/login.js"></script>
</body>
</html>