
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="reply.model.*" %>
<%@ page import="java.util.*"%>
<%
List<Reply> list = (List<Reply>) request.getAttribute("replyList");
int size = list.size();
request.setAttribute("size", size);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
<script>
	function removeArticle() {
		if (confirm("삭제하시겠습니까?") == true) {
			document.getElementById('removeArticle-form').submit();
		} else {
			return;
		}
	};
</script>
</head>
<body>
	<u:navbar />

	<div class="container">
		<br />
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h3>게시글 읽기</h3>
				<br />
				<div class="text-right mb-2">

					<a href="${root }/article/read.do?no=${articleData.prevNumber}"><button
							type="button" id="btn-prev" class="btn btn-primary btn-sm"
							<c:if test="${ articleData.prevNumber eq 0 }">
										disabled
									</c:if>>이전글</button></a>

					<a href="${root }/article/read.do?no=${articleData.nextNumber}"><button
							type="button" id="btn-next" class="btn btn-primary btn-sm"
							<c:if test="${ articleData.nextNumber eq 0 }">
										disabled
									</c:if>>다음글</button></a>
				</div>
				<table class="table table-bordered table-sm">
					<tbody>
						<tr>
							<td width="15%">번호</td>
							<td width="35%">${articleData.article.number }</td>
							<td width="15%">작성일</td>
							<td width="35%"><fmt:formatDate
									value="${articleData.article.regDate }"
									pattern="yyyy-MM-dd hh:mm" /></td>
						</tr>

						<tr>
							<td width="15%">작성자</td>
							<td width="35%">${articleData.article.writer.name }</td>
							<td width="15%">수정일</td>
							<td width="35%"><fmt:formatDate
									value="${articleData.article.modifiedDate }"
									pattern="yyyy-MM-dd hh:mm" /></td>
						</tr>
						<tr>
							<td>제목</td>
							<td colspan="3"><c:out value="${articleData.article.title }"></c:out></td>
						</tr>
						<tr>
							<td colspan="4" class="text-center">내용</td>
						</tr>
						<tr>
							<td colspan="4">
								<div style="height: 250px; overflow:scroll;"><u:pre value="${articleData.content }"/></div>
							</td>

						</tr>
					</tbody>
				</table>

				<div class="d-flex bd-highlight mb-3">
					<div class="mr-auto bd-highlight">

						<c:set var="pageNo"
							value="${empty param.pageNo ? '1' : param.pageNo }" />
						<a href="${root }/article/list.do?pageNo=${pageNo }"><button
								type="submit" class="btn btn-primary">목록</button></a>
					</div>

					<c:if test="${authUser.id == articleData.article.writer.id || authUser.id == 'admin'}">
						<div class="pr-1 bd-highlight">
							<a
								href="${root }/article/modify.do?no=${articleData.article.number }"><button
									type="submit" class="btn btn-primary">수정</button></a>
						</div>

						<form action="${root }/article/remove.do" id="removeArticle-form"
							method="POST">
							<input type="text" name="removeNo"
								value="${articleData.article.number }" hidden />
							<div class="bd-highlight">
								<button type="button" class="btn btn-primary" onclick="removeArticle();">삭제</button>

							</div>
						</form>
					</c:if>
				</div>

				<u:listReply />
				<hr />
					<u:replyForm articleNo="${articleData.article.number }" />

				<div class="col-3"></div>
			</div>

		</div>

	</div>
</body>
</html>