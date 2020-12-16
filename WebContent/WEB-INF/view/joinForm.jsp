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
<title>회원 가입</title>

</head>
<body>
<u:navbar/>
<div class="container">

	<div class="row">
		<div class="col-4">
		</div>
		<div class="col-4">

			<h3>회원가입</h3>
<br />
<form action="${root }/join.do" method="post">
<div class="form-group">
    <label for="input1-id">아이디</label>
    <input type="text" class="form-control" name="id" value="${param.id}" id="input1-id" style="border-color: #608df9">
    <c:if test="${errors.id }">
    <small class="form-text text-danger">ID를 입력하세요.</small></c:if>
        <c:if test="${errors.duplicateId }">
    <small class="form-text text-danger">이미 사용중인 아이디입니다.</small></c:if>
  </div>
<div class="form-group">
    <label for="input2-name">이름</label>
    <input type="text" class="form-control" name="name" value="${param.name}" id="input2-name" style="border-color: #608df9">
    <c:if test="${errors.name }"> 
    <small class="form-text text-danger">이름을 입력하세요.</small></c:if>
  </div>
  <div class="form-group">
    <label for="input3-pw">암호</label>
    <input type="password" class="form-control" id="input3-pw" name="pw" style="border-color: #608df9">
    <c:if test="${errors.pw }">
     <small class="form-text text-danger">암호를 입력하세요.</small></c:if>
  </div>
  <div class="form-group">
    <label for="input4-comfirmPw">암호확인</label>
    <input type="password" class="form-control" id="input4-comfirmPw" name="confirmPw" style="border-color: #608df9">
        <c:if test="${errors.confirmPw }">
     <small class="form-text text-danger">다시 한 번 암호를 입력하세요.</small></c:if>
             <c:if test="${errors.notMatch }">
     <small class="form-text text-danger">암호가 일치하지 않습니다.</small></c:if>
  </div>
  <div class="form-group">
    <label for="input5-email">이메일</label>
    <input type="text" class="form-control" name="email" value="${param.email}" id="input5-email" style="border-color: #608df9">
    <c:if test="${errors.email }"> 
    <small class="form-text text-danger">이메일을 입력하세요.</small></c:if>
  </div>
  		<div class="text-right">
  <button type="submit" class="btn btn-primary" style="width: 100%">회원 가입</button></div>
</form>
			</div>

		<div class="col-4">

		</div>
	</div>


</div>
<u:joinSucMs/>
</body>
</html>