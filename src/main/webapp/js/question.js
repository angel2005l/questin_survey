$(function() {
	$("#saveBtn").on("click", function() {
		if (check()) {
			$("#saveBtn").attr("disabled","disbaled");
			$("#subForm").ajaxSubmit({
				url : "question?method=answer",
				type : "post",
				dataType : "json",
				success : function(result) {
					console.log(result);
					if (result.status == 0) {
						alert(result.msg);
						location.href="/emp/view/successForPart.jsp"
					} else {
						alert(result.msg)
						location.href="question?method=question"
					}
				}
			});
		} else {
			return;
		}

	})
});

function check() {
	var isSuccess = true;
	$("input[type='text']").each(function() {
		if ($(this).val() == '') {
			alert("[" + $(this).parent().prev().html() + "]不能为空");
			isSuccess = false;
			return false;
		}
	});
	if (isSuccess) {
		$(".que-radio").each(function() {
			if ($(this).children("input[type='radio']:checked").length == 0) {
				alert("[" +$(this).prev().html() + "]未选择");
				isSuccess = false;
				return false;
			}
		});
	}
	if (isSuccess) {
		$(".que-tr").each(function() {
			if ($(this).find("input[type='radio']:checked").length == 0) {
				alert("[" + $(this).find("td").eq(0).html() + "]未选择");
				isSuccess = false;
				return false;
			}
		});
	}
	return isSuccess;
}
