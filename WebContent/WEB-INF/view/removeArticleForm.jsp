<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
$(function() {
	$("#btn-remove").click(function() {
		alert("삭제 하시겠습니까?");
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>게시글 삭제</h1>
<form action="remove.do" method="POST">
<p>
<input type="hidden" name="removeNo" value="${modReq.articleNumber}">
번호 : ${modReq.articleNumber}
</p>
<p>
제목 : <br />
<input type="text" name="title" value="${modReq.title}" />
<c:if test="${errors.title }">제목을 입력하세요</c:if>
</p>
<p>
내용 : <br />
<textarea name="content" cols="30" rows="5" wrap="hard">${modReq.content }</textarea>
</p>
  <button class="btn btn-danger" id="btn-remove" name="removeNo" value="${modReq.articleNumber}">삭제</button>
</form>
</div>
</body>
</html>