var accountIpunt = $("#user_account");
var pwdInput = $("#user_pwd");
$(function() {
	accountIpunt.keydown(function(e) {
		if (13 == e.keyCode) {
			pwdInput.focus();
		}
	});
	pwdInput.keydown(function(e) {
		if (e.keyCode == 13) {
			$("#submit").click();
		}
	});
});

function login() {
	var userAccount = accountIpunt.val();
	var userPass = pwdInput.val();
	//alert("出发");
	if ('' == userAccount) {
		alert("请输入用户名")
		accountIpunt.focus();
	} else if ('' == userPass) {
		alert("请输入密码");
		pwdInput.focus();
	} else {
		$.ajax({
			type : 'post',
			url : "indexController",
			data : {'method':'login','userAccount':userAccount ,'userPass':userPass},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				console.log(data.status)
				if(data.status!='success'){
					alert("登录失败");
					window.location.href ="/emp/login.jsp";
				}else {
					window.location.href="/emp/indexController?method=index";
				}
			},
			error : function() {
				alert("服务器无响应");
			}
		});

	}
}

