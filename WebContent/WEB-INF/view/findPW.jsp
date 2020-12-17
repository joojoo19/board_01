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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="css/custom.css">

<title>joojoo's Home</title>
</head>
<body>
<u:navbar/>
<div class="container">
<div class="row">
	<div class="col-4">
	</div>
	<div class="col-4">
		<h3>비밀번호찾기</h3>
	<br />
<form action="${root }/findpw.do" method="post">
<div class="form-group">
    <label for="input1-id">아이디</label>
    <input type="text" class="form-control" name="id" value="${param.id}" id="input1-id" style="border-color: #608df9">
    <c:if test="${errors.id }">
    <small class="form-text text-danger">아이디를 입력하세요.</small></c:if>
  </div>
<div class="form-group">
    <label for="input2-name">이름</label>
    <input type="text" class="form-control" name="name" value="${param.name}" id="input2-name" style="border-color: #608df9">
    <c:if test="${errors.name }">
    <small class="form-text text-danger">이름 입력하세요.</small></c:if>
  </div>
  <div class="form-group">
    <label for="input3-email">이메일</label>
    <input type="text" class="form-control" id="input3-email" name="email" value="${param.email}" style="border-color: #608df9">
    <c:if test="${errors.email }">
     <small class="form-text text-danger">이메일을 입력하세요.</small></c:if>
  </div>
  		<div class="text-right">
  <button type="submit" class="btn btn-primary" style="width: 100%">비밀번호 찾기</button></div>
  
</form>
<div class="d-flex bd-highlight">
  <div class="p-2 flex-fill bd-highlight"><a href="${root }/findid.do">아이디찾기</a></div>
  <div class="p-2 flex-fill bd-highlight"><a href="${root }/login.do">로그인</a></div>
  <div class="p-2 flex-fill bd-highlight"><a href="${root }/join.do">회원가입</a></div>
</div>
	</div>
</div>
	<div class="col-4">

	</div>
<u:findIdMs/>
</div>
</body>
</html>