<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/custom.css">
<style type="text/css">
	.container {
		margin-top: 10%;
	}
	.box-border {
		border: solid 1px #608df9;
		margin: 5px;
		width : 250px;
		height: 40px;
	}
	.btn-submit {
		background-color: #608df9;
		border: solid 1px #608df9;
		color: white;
		margin: 3px;
		width : 250px;
		height: 40px;
	}

</style>
<title>로그인</title>
</head>
<body>
<div class="container" align="center">
<h4 style="margin: 15px">로 그 인</h4>
<input type="text" name="id" class="box-border" placeholder="아이디"/><br />
<input type="text" name="pw" class="box-border" placeholder="비밀번호"/><br />
<button type="submit" class="btn-submit" >로그인</button>
<p> <a href="findid.do">아이디 찾기</a> <a href="findpw.do">비밀번호 찾기</a> <a href="join.do">회원가입</a> </p>
</div>
<!-- 로그인 성공 메세지 -->
<u:joinSucMs/>

</body>
</html>