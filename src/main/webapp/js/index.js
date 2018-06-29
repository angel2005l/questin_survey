$(function() {
	// 点击查询
	$("#queryCommit").on('click', function() {
		query("q");
	});
	$("#addDeatil").on("click",function(){
		var tag = "<tr><td><input  type='text'></td><td><button class='del'>删除</button></td></tr>"
		$("#queDetail").append(tag);
	})
	$(document).on("click",".del",function(){
		$(this).parents('tr').remove();
	});
	
	
})


function query(type) {
	var pageObj = $("#page");
	var page = pageObj.val();
	var count = $("#count").val();
	// 获得最大页数
	var pageNum = Math.ceil(count / 10);
	switch (type) {
	case "q":
		page = 1;
		break;
	case "up":
		if (page > 1) {
			page--;
		}
		break;
	case "down":
		if (page < pageNum) {
			page++;
		}
		break;
	default:
		break;
	}
	pageObj.val(page);
	$("#queryForm").submit();
}

$(document).ready(function() {
	// 弹出文本性提示框
	$("#insQueBtn").click(function() {
		$("#insQue").fadeIn();
	});

	$("#insSaveBtn").on("click", function() {
		var queTitle = $("#insTitle").val();
		var queLevel = $("#insLevel").val();
		var queSeq = $("#insSeq").val();
		var queType = $("#insType").val();
		var queIsAns = $('#insIsAns').prop('checked') ? 'Y' : 'N';
		var queShow = $('#insShow').prop('checked') ? 'Y' : 'N';
		var details = "";
		$("#queDetail input[type='text']").each(function() {
			var detail = $(this).val();
			if (null != detail && '' != detail) {
				details += (detail+",");
			}
		});
		
		//数据校验
		var isSuccess = true;
		if (null == queTitle || '' == queTitle) {
			alert("题目名称不能为空");
			isSuccess = false;
		}
		if (null == queLevel || '' == queLevel) {
			alert("题目级别不能为空");
			isSuccess = false;
		}
		var reg = /^-?[0-9]\d*$/;
		if (null == queSeq || '' == queSeq) {
			alert("题目顺序不能为空")
			isSuccess = false;
		} else if (!reg.test(queSeq)) {
			alert("题目顺序必须为正整数或0");
			isSuccess = false;
		}
		var reg = /^-?[0-9]\d*$/;
		if (null == queSeq || '' == queSeq) {
			alert("题目顺序不能为空")
			isSuccess = false;
		} else if (!reg.test(queSeq)) {
			alert("题目顺序必须为正整数或0");
			isSuccess = false;
		}
		if (null == queType || '' == queType) {
			alert("请选择题目类型")
			isSuccess = false;
		}
		if (isSuccess) {
			$.ajax({
				type : 'post',
				url : "indexController",
				data : {
					'method' : 'addQue',
					'queTitle' : queTitle,
					'queLevel' : queLevel,
					'queSeq' : queSeq,
					'queType' : queType,
					'queIsAns' : queIsAns,
					'queShow' : queShow,
					'details' : details
				},
				dataType : 'json',
				success : function(result) {
					if (result.status != 'success') {
						alert(result.msg);
						return;
					} else {
						alert(result.msg);
						$("#insQue").fadeOut();
						query('q');
					}
				},
				error : function() {
					alert("服务器无响应");
				}
			});
		} else {
			return;
		}
	});

	$("#editSaveBtn").on("click", function() {
		var queSeq = $("#editSeq").val();
		var queShow = $('#editShow').prop('checked') ? 'Y' : 'N';
		var queId = $("#editId").val();
		var isSuccess = true;
		var reg = /^-?[0-9]\d*$/;
		if (null == queSeq || '' == queSeq) {
			alert("问卷顺序号不能为空")
			isSuccess = false;
		} else if (!reg.test(queSeq)) {
			alert("问卷顺序号必须为正整数或0");
			isSuccess = false;
		}
		if (isSuccess) {
			$.ajax({
				type : 'post',
				url : "indexController",
				data : {
					'method' : 'editQueForSave',
					'queSeq' : queSeq,
					'queShow' : queShow,
					'queId' : queId,
				},
				dataType : 'json',
				success : function(data) {
					if (data.status != 'success') {
						alert(errMsg);
						return;
					} else {
						alert(data.msg);
						$("#editQue").fadeOut();
						query('q');
					}
				},
				error : function() {
					alert("服务器无响应");
				}
			});
		} else {
			return;
		}
	});

	$("#editCloseBtn").click(function() {
		$("#editQue").fadeOut();
		$("#editSeq").val("");
		$("#editId").val("");
		$("#editShow").attr("checked", "checked");
	});

	$("#insCloseBtn").click(function() {
		$("#insQue").fadeOut();
		$("#insTitle").val("");
		$("#insLevel").val("");
		$("#insSeq").val("");
		$("#insType").val("");
		$("#insIsAns").attr("checked", "checked");
		$("#insShow").attr("checked", "checked");
		$("#queDetail").empty();

	});

});

function queEdit(data) {
	$.ajax({
		async : false,
		type : 'post',
		url : "indexController",
		data : {
			method : 'editQueForInfo',
			queId : data
		},
		dataType : 'json',
		success : function(result) {
			// console.log(data);
			if (result.status != 'success') {
				alert(result.msg);
			} else {
				var que = result.data;
				// console.log(job);
				$("#editSeq").val(que.queSeq);
				$("#editId").val(que.id);
				if ('Y' == que.queShow) {
					$("#editShow").attr("checked", "checked");
				} else {
					$("#editShow").removeAttr("checked");
				}
			}
		},
		error : function() {
			alert("连接服务器异常")
		}

	});
	$("#editQue").fadeIn();
}
