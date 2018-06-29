<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>问卷后台管理系统</title>
<link rel="stylesheet" type="text/css" href="/emp/css/style.css">
<link rel="stylesheet" type="text/css" href="/emp/css/index.css">
<!--[if lt IE 9]>
<script src="../style/js/html5.js"></script>
<![endif]-->
<script src="/emp/js/jquery.js"></script>
<script src="/emp/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="/emp/js/main.js"></script>
</head>

<body>
	<!--header-->
	<header>
		<h1>
			<img src="/emp/img/xinhai.png" />
		</h1>
		<ul class="rt_nav">
			<li><a href="#" class="admin_icon">${sessionScope.user.userName}</a></li>
			<li><a href="indexController?method=quit" class="quit_icon">安全退出</a></li>
		</ul>
	</header>
	<!--aside nav-->
	<!--aside nav-->
	<aside class="lt_aside_nav content mCustomScrollbar">
		<h2>
			<a href="#"></a>
		</h2>
		<ul>
			<li>
				<dl>
					<dt>问卷</dt>
					<!--当前链接则添加class:active-->
					<dd>
						<a href="indexController?method=index">问卷信息</a>
					</dd>
				</dl>
			</li>
		</ul>
	</aside>
	<section class="rt_wrap content mCustomScrollbar">
		<div class="rt_content">
			<div class="page_title">
				<h2 class="fl">问卷信息</h2>
				<a id="insQueBtn" class="fr top_rt_btn add_icon">添加问题</a>
			</div>
			<section class="mtb">
				<form id="queryForm" action="indexController?method=selQue"
					method="post">
					<input id="page" name="page" type="hidden"
						value="${requestScope.page }"> <input id="count"
						name="count" type="hidden" value="${requestScope.count }">
					<label class="single_selection"><input type="checkbox"
						id="isShow" name="isShow"
						<c:if test="${requestScope.queryQueShow eq 'Y' }">checked</c:if>
						value="Y" />只看显示</label> <input id="queryCommit" type="button" value="查询"
						class="group_btn" />
				</form>
			</section>
			<table class="table">
				<tr>
					<th>序号</th>
					<th>题目名称</th>
					<th>类型</th>
					<th>是否必答</th>
					<th>顺序</th>
					<!-- <th>内容</th> -->
					<th>是否显示</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${requestScope.data }" var="b" varStatus="s">
					<tr title="${b.queDetail }" >
						<td class="center">${s.count }</td>
						<td class="center">${b.que.queTitle }</td>
						<td class="center"><c:choose>
								<c:when test="${b.que.queType =='text' }">填空题</c:when>
								<c:when test="${b.que.queType =='radio' }">单选题</c:when>
								<c:when test="${b.que.queType =='checkbox' }">多选题</c:when>
								<c:when test="${b.que.queType =='radios' }">矩阵量表图</c:when>
							</c:choose></td>
						<td class="center"><c:choose>
								<c:when test="${b.que.queIsAns =='Y' }">是</c:when>
								<c:when test="${b.que.queIsAns =='N' }">否</c:when>
							</c:choose></td>
						<td class="center">${b.que.queSeq }</td>
						<%-- <td class="center"><c:forEach items="${b.qdList }" var="d">
							[${d.detContext }]
						</c:forEach></td> --%>
						<td class="center"><a class="link_icon"> <c:if
									test="${b.que.queShow eq 'Y' }">Y</c:if> <c:if
									test="${b.que.queShow eq 'N'}">X</c:if></a></td>
						<td class="center"><a onclick="queEdit(${b.que.id})"
							title="编辑" class="link_icon">&#101;</a></td>
					</tr>
				</c:forEach>
			</table>
			<aside class="paging">
				<a id="pageUp" onclick="query('up')">上一页</a> <a id="pageDown"
					onclick="query('down')">下一页</a><font>&nbsp;&nbsp;&nbsp;共
					${requestScope.count} 条</font>

			</aside>
		</div>
	</section>
	<section class="pop_bg" id="insQue">
		<div class="pop_cont">
			<!--title-->
			<h3>添加问题</h3>
			<!--content-->
			<div class="pop_cont_input">
				<ul>
					<li><span>题目名称</span> <input id="insTitle" type="text"
						placeholder="例：姓名..." class="textbox" /></li>
					<li><span>题目级别</span> <input id="insLevel" type="text"
						placeholder="例：第一部分 :1,第二部分：2" class="textbox" /></li>
					<li><span>题目顺序</span> <input id="insSeq" type="text"
						placeholder="例：4" class="textbox" /></li>
					<li><span class="ttl">题目类型</span> <select id="insType"
						class="select" style="width: 210px">
							<option value="">请选择题目类型</option>
							<option value="radio">单选题</option>
							<option value="checkbox">多选题</option>
							<option value="text">填空题</option>
							<option value="radios">矩阵量表图</option>
					</select></li>
					<li><label class="single_selection"><input
							id="insIsAns" type="checkbox" checked="checked" value="Y" />是否必填</label></li>
					<li><label class="single_selection"><input
							id="insShow" type="checkbox" checked="checked" value="Y" />是否显示</label></li>
					<li><span>题目要求</span>
						<button id="addDeatil">添加</button></li>
					<li>
						<div id="scrollDiv">
						<table id ="queDetail">
						</table>
						</div>
					</li>
				</ul>
				<!--bottom:operate->button-->
				<div class="btm_btn">
					<input type="button" value="确认" class="input_btn trueBtn"
						id="insSaveBtn" /> <input type="button" value="关闭"
						class="input_btn falseBtn" id="insCloseBtn" />
				</div>
			</div>
		</div>
	</section>
	<section class="pop_bg" id="editQue">
		<div class="pop_cont">
			<!--title-->
			<h3>编辑题目</h3>
			<!--content-->
			<div class="pop_cont_input">
				<ul>
					<li><span>题目顺序</span> <input id="editSeq" type="text"
						placeholder="例：1" class="textbox" /></li>
					<li><label class="single_selection"><input
							id="editShow" type="checkbox" checked="checked" value="Y" />是否显示</label></li>

				</ul>
				<input type="hidden" id="editId" />
				<!--bottom:operate->button-->
				<div class="btm_btn">
					<input type="button" value="确认" class="input_btn trueBtn"
						id="editSaveBtn" /> <input type="button" value="关闭"
						class="input_btn falseBtn" id="editCloseBtn" />
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript" src="/emp/js/index.js"></script>
</body>

</html>