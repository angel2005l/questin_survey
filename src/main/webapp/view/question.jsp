<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<title>问卷调查</title>
<link rel="stylesheet" href="/emp/css/question.css" />
<script type="text/javascript" src="/emp/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/emp/js/jquery.form.js"></script>
</head>
<body>
	<div class="que-head">
		<h1>关于变革型领导的调查问卷（员工评价）</h1>
		<font>尊敬的女士/先生:</font>
		<p>您好！感谢您在百忙之中参与此次文件调查</p>
		<p>本问卷旨在研究检验组织中变革型领导对创新绩效的影响，大约需花费您5-10分钟左右的时间填写。本次问卷调查目的仅用于学术研究而没有任何商业用途，一切信息将严格保密，请放心填写</p>
		<hr>
	</div>

	<div class="que-context">
		<form id="subForm">
			<input type="hidden" name="naireCode" value="${requestScope.naireCode }"> 
			<p>第一部分:基本资料，请您根据自己的实际情况填写并选择相应的选项</p>
			<!--题目-->
			<c:forEach items="${requestScope.queData }" var="b">
				<c:if test="${b.queLevel ==1 }">
					<c:choose>
						<c:when test="${b.queType eq 矩阵量表图 } ">
							<div class="que-info">
								<p>您所在部门的名称【矩阵量表图】*</p>
								<div class="que-table">
									<table class="que-table">
										<tr>
											<th class="que-th1"></th>
											<th class="que-th2">非常不同意</th>
											<th class="que-th3">不同意</th>
											<th class="que-th4">不确定</th>
											<th class="que-th5">同意</th>
											<th class="que-th6">非常同意</th>
										</tr>
										<c:forEach items="${requestScope.qdData }" var="d">
											<c:if test="${d.queId == b.id }">
												<tr>
													<td class="leftString">${d.detContext }</td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="1" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="2" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="3" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="4" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="5" /></td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:when>
						<c:when test="${b.queType eq '填空题' }">
							<div class="que-info">
								<p>${b.queTitle }【填空题】*</p>
								<!--题目内容-->
								<div class="que-input <c:if test="${b.queType eq '填空题' }">que-text</c:if>">
									<input type="text" name="${b.id }" />
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="que-info">
								<p>${b.queTitle }【${b.queType }】<c:if
										test="${b.queIsAns eq 'Y' }">*</c:if>
								</p>
								<!--题目内容-->
								<div class="que-input que-radio">
									<c:forEach items="${requestScope.qdData }" var="d">
										<c:if test="${d.queId eq b.id }">
											<input type="${b.queInputType }" name="${b.id }"
												value="${d.detContext }">${d.detContext }</c:if>
									</c:forEach>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			<p>第二部分:请回答，在多大程度上，您同意以上关于您的领导的描述，选择适当的选项。</p>
			<c:forEach items="${requestScope.queData }" var="b">
				<c:if test="${b.queLevel ==2 }">
					<c:choose>
						<c:when test="${b.queType eq'矩阵量表图' }">
							<div class="que-info">
								<p>【矩阵量表图】*</p>
								<div class="que-table">
									<table class="que-table">
										<tr>
											<th class="que-th1"></th>
											<th class="que-th2">非常不同意</th>
											<th class="que-th3">不同意</th>
											<th class="que-th4">不确定</th>
											<th class="que-th5">同意</th>
											<th class="que-th6">非常同意</th>
										</tr>
										<c:forEach items="${requestScope.qdData }" var="d">
											<c:if test="${d.queId eq b.id }">
												<tr class = "que-tr">
													<td class="leftString">${d.detContext }</td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="1" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="2" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="3" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="4" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="5" /></td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:when>
						<c:when test="${b.queType eq '填空题' }">
							<div class="que-info">
								<p>${b.queTitle }【填空题】*</p>
								<!--题目内容-->
								<div class="que-input">
									<input type="text" name="${b.id }" />
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="que-info">
								<p>${b.queTitle }【${b.queType }】<c:if
										test="${b.queIsAns eq 'Y' }">*</c:if>
								</p>
								<!--题目内容-->
								<div class="que-input">
									<c:forEach items="${requestScope.qdData }" var="d">
										<c:if test="${d.queId eq b.id }">
											<input type="${b.queInputType }" name="${b.id }"
												value="${d.detContext }">${d.detContext }</c:if>
									</c:forEach>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			<p>第三部分:请回答，在多大程度上，您同意以上关于您的领导的描述，选择适当的选项。</p>
			<c:forEach items="${requestScope.queData }" var="b">
				<c:if test="${b.queLevel ==3 }">
					<c:choose>
						<c:when test="${b.queType eq '矩阵量表图' }">
							<div class="que-info">
								<p>【矩阵量表图】*</p>
								<div class="que-table">
									<table class="que-table">
										<tr>
											<th class="que-th1"></th>
											<th class="que-th2">非常不同意</th>
											<th class="que-th3">不同意</th>
											<th class="que-th4">不确定</th>
											<th class="que-th5">同意</th>
											<th class="que-th6">非常同意</th>
										</tr>
										<c:forEach items="${requestScope.qdData }" var="d">
											<c:if test="${d.queId eq b.id }">
												<tr class ="que-tr">
													<td class="leftString">${d.detContext }</td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="1" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="2" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="3" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="4" /></td>
													<td><input type="radio" name="${b.id }-${d.id }"
														value="5" /></td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:when>
						<c:when test="${b.queType eq'填空题' }">
							<div class="que-info">
								<p>${b.queTitle }【填空题】*</p>
								<!--题目内容-->
								<div class="que-input">
									<input type="text" name="${b.id }" />
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="que-info">
								<p>${b.queTitle }【${b.queType }】<c:if
										test="${b.queIsAns eq 'Y' }">*</c:if>
								</p>
								<!--题目内容-->
								<div class="que-input">
									<c:forEach items="${requestScope.qdData }" var="d">
										<c:if test="${d.queId eq b.id }">
											<input type="${b.queInputType }" name="${b.id }"
												value="${d.detContext }">${d.detContext }</c:if>
									</c:forEach>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			<footer>
				<hr />
				<input id="saveBtn" type="button" value="提交" /> <input
					type="button" value="关闭" />
			</footer>
		</form>
	</div>
	<script type="text/javascript" src="/emp/js/question.js"></script>
</body>
</html>
