<%@page import="notice.model.Notice"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<style>
.notice_style {
	font-weight: bold;
	background-color: #F0F0F0;
}

a {
	color: black;
	text-decoration: none;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}

a:active {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body>
	<u:navbar />
	<div class="container">
		<div class="row">
			<div class="col-1">
				<div></div>
			</div>
			<div class="col-10">
				<div>
					<h3>게시글 목록</h3>
					<br />
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col" width="10%" class="text-center">번호</th>
								<th scope="col" width="51%" class="text-center">제목</th>
								<th scope="col" width="16%" class="text-center">작성자</th>
								<th scope="col" width="16%" class="text-center">작성일</th>
								<th scope="col" width="7%" class="text-center">조회</th>
							</tr>
						</thead>
						<tbody>
							<!-- 공지사항  -->

							<c:forEach var="notice" items="${noticePage.content }">

								<tr class="notice_style">
									<th scope="row" class="text-center">공지</th>
									<td><a href="${root }/notice/read.do?no=${notice.number}">
											<c:out value="${notice.title }" />
									</a>&nbsp;&nbsp;<c:if test="${notice.replyCount > 0}">[${notice.replyCount }]</c:if></td>
									<td class="text-center" style="font-weight: normal;">${notice.writer.name }</td>
									<td class="text-center" style="font-weight: normal;"><fmt:formatDate
											value="${notice.regDate }" pattern="yyyy-MM-dd" /></td>
									<td class="text-center" style="font-weight: normal;">${notice.readCount }</td>
								</tr>

							</c:forEach>

							<c:if test="${errors.searchFail }">
								<tr>
									<td colspan="5" class="text-center"><span
										style="font-size: 20px; color: gray;"> <i
											class="fas fa-exclamation-triangle"></i>검색 결과가 없습니다.
									</span></td>
								</tr>
							</c:if>

							<!-- 게시글 -->
							<c:forEach var="article" items="${articlePage.content }">
								<tr>
									<th scope="row" class="text-center"
										style="font-weight: normal;">${article.number }</th>
									<td><a
										href="read.do?no=${article.number}&pageNo=${articlePage.currentPage }">
											<c:out value="${article.title }" />
									</a>&nbsp;<c:if test="${article.replyCount > 0}">[${article.replyCount }]</c:if></td>
									<td class="text-center">${article.writer.name }</td>
									<td class="text-center"><fmt:formatDate
											value="${article.regDate }" pattern="yyyy-MM-dd" /></td>
									<td class="text-center">${article.readCount }</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="text-right">
						<a href="${root }/article/write.do"><button type="submit"
								class="btn btn-primary">글쓰기</button></a>
					</div>
					<div>
						<form action="${root }/article/search.do" method="post"
							id="articleSearch-form">
							<input type="text" name="pageNo"
								value="${articlePage.currentPage }" hidden /> <select
								name="search" id="">
								<option selected>검색</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="writer_name">작성자</option>
							</select><input type="text" name="keyword" value="${param.keyword }" />
							<button type="submit" class="btn btn-primary btn-sm mb-1">검색</button>
						</form>
					</div>
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:if test="${articlePage.startPage > 5}">
								<li class="page-item"><a class="page-link"
									href="${root }/article/list.do?pageNo=${articlePage.startPage - 5 }"><i
										class="fas fa-angle-double-left"></i></a></li>
							</c:if>

							<c:forEach begin="${articlePage.startPage }"
								end="${articlePage.endPage }" var="pNo">
								<li class="page-item"><a class="page-link"
									href="${root }/article/list.do?pageNo=${pNo}">${pNo }</a></li>

							</c:forEach>
							<c:if test="${articlePage.endPage < articlePage.totalPages }">
								<li class="page-item"><a class="page-link"
									href="${root }/article/list.do?pageNo=${articlePage.startPage + 5 }"><i
										class="fas fa-angle-double-right"></i></a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>

			<div class="col-2">
				<div></div>
			</div>
		</div>


	</div>
</body>
</html>