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
<script>
	function changeName() {
		if (confirm("이름을 변경 하시겠습니까?") == true) {
			document.getElementById('changeName-form').submit();
		} else {
			return;
		}
	};
	function changeEmail() {
		if (confirm("이메일을 변경 하시겠습니까?") == true) {
			document.getElementById('changeEmail-form').submit();
		} else {
			return;
		}
	};
</script>
</head>
<body>
<u:navbar/>
<div class="container">
<div class="row">
	<div class="col-4"></div>
	<form action="${root}/" method="post" id="changeName-form">
	  <div class="form-group">
    <label for="input1-name">이메일</label>
    <input type="text" class="form-control" id="input1-name" name="name" value="${param.name}" style="border-color: #608df9">
    <c:if test="${errors.email }">
     <small class="form-text text-danger">이메일을 입력하세요.</small></c:if>
         <c:if test="${errors.notMember }">
     <small class="form-text text-danger">회원이 아닙니다.</small></c:if>
     	
  </div>
  </form>
    		<div class="text-right">
  <button type="button" class="btn btn-primary" style="width: 100%" id="changeName-btn" onclick="changeName();">변경</button></div>
  
  	<form action="${root}/" method="post" id="changeEmail-form">
	  <div class="form-group">
    <label for="input2-email">이메일</label>
    <input type="text" class="form-control" id="input2-email" name="email" value="${param.email}" style="border-color: #608df9">
    <c:if test="${errors.email }">
     <small class="form-text text-danger">이메일을 입력하세요.</small></c:if>
         <c:if test="${errors.notMember }">
     <small class="form-text text-danger">회원이 아닙니다.</small></c:if>
     	
  </div>
  </form>
  		<div class="text-right">
  <button type="button" class="btn btn-primary" style="width: 100%" id="changeEmail-btn" onclick="changeEmail();">변경</button></div>
  

	<div class="col-4"></div>
	<div class="col-4"></div>
</div>
</div>
<u:copyright/>
</body>
</html>