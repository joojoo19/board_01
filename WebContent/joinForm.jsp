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
<style type="text/css">
	.container {
		margin-top: 6%;
	}
	.box-title {
		width : 250px;
		height: 40px;
		text-align: left;
		margin-bottom: -12px;
	}
	.box-border {
		border: solid 1px #608df9;
		margin-bottom: 6px;
		width : 250px;
		height: 40px;
	}
	.btn-submit {
		background-color: #608df9;
		border: solid 1px #608df9;
		color: white;
		width : 250px;
		height: 40px;
	}

</style>
<title>회원가입</title>
</head>
<body>
<div class="container" align="center">
<h4>회원 가입 양식</h4><br />
<form action="join.bo" method="post">
<div class="box-title"><p>아이디</p></div>
<input type="text" name="id" class="box-border" /><br />
<div class="box-title"><p>비밀번호</p></div>
<input type="password" name="pw" class="box-border" /><br />
<div class="box-title"><p>비밀번호확인</p></div>
<input type="password" name="confirmpw" class="box-border" /><br />
<div class="box-title"><p>이름</p></div>
<input type="text" name="name" class="box-border" /><br />
<div class="box-title"><p>생년월일</p></div>
<input type="text" name="birth" class="box-border" placeholder="yyyy-mm-dd"/><br />
<div class="box-title"><p>이메일</p></div>
<input type="email" name="email" class="box-border" /><br />
<button type="submit" class="btn-submit" >회원가입</button>
</form>
</div>
</body>
</html>