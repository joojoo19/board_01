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
<style type="text/css">
	h6 {
	color: red;
	}
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
<script type="text/javascript">
$("input").focus(function() {
	$(this).css("border-color", "#608df9"); });
</script>
<title>회원가입</title>
</head>
<body>
<div class="container" align="center">
<h4>회원 가입 양식</h4><br />
<form action="join.do" method="post">

<div class="box-title"><p>아이디</p></div>
<input type="text" name="id" value="${param.id }" class="box-border" /><br />
<c:if test="${errors.id }"><h6>ID를 입력하세요 </h6></c:if>
<c:if test="${errors.duplicateId }"><h6>이미 사용중인 아이디입니다 </h6></c:if>

<div class="box-title"><p>비밀번호</p></div>
<input type="password" name="pw" class="box-border" /><br />
<c:if test="${errors.pw }"><h6>비밀번호를 입력하세요 </h6></c:if>

<div class="box-title"><p>비밀번호확인</p></div>
<input type="password" name="confirmPw" class="box-border" /><br />
<c:if test="${errors.confirmPw }"><h6>비밀번호를 다시 한 번 입력하세요</h6> </c:if>
<c:if test="${errors.notMatch}"><h6>암호와 확인이 일치하지 않습니다 </h6></c:if>

<div class="box-title"><p>이름</p></div>
<input type="text" name="name" value="${param.name }" class="box-border" /><br />
<c:if test="${errors.name }"><h6>이름을 입력하세요</h6> </c:if>

<div class="box-title"><p>이메일</p></div>
<input type="email" name="email" class="box-border"  value="${param.email }" /><br />
<c:if test="${errors.email }"><h6>이메일을 입력하세요 </h6><br /></c:if>
<button type="submit" class="btn-submit" >회원가입</button>
</form>
</div>
<u:joinSucMs/>
</body>
</html>