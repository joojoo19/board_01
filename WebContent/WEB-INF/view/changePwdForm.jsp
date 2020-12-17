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
<title>joojoo's Home</title>
</head>
<body>
	<u:navbar/>
	<div class="container">
	<div class="row">
		<div class="col-4">
		</div>
		<div class="col-4">
			<h3>암호변경</h3>
			<br />
<form action="${root }/changePwd.do" method="post">
 <div class="form-group">
    <label for="input3-pw">현재 암호</label>
    <input type="password" class="form-control" id="input3-pw" name="curPwd" style="border-color: #608df9">
    <c:if test="${errors.curPwd }">
     <small class="form-text text-danger">암호를 입력하세요.</small></c:if>
         <c:if test="${errors.badCurPwd }">
     <small class="form-text text-danger">현재 암호가 일치하지 않습니다.</small></c:if>
  </div>
  <div class="form-group">
    <label for="input4-comfirnPw">새 암호</label>
    <input type="password" class="form-control" id="input4-comfirnPw" name="newPwd" style="border-color: #608df9">
        <c:if test="${errors.newPwd }">
     <small class="form-text text-danger">새 암호를 입력하세요.</small></c:if>
            <c:if test="${errors.badNewPwd }">
     <small class="form-text text-danger">현재 암호와 일치합니다. 다른 암호를 입력해주세요.</small></c:if>
  </div>
		<div class="text-right"><button type="submit" class="btn btn-primary">암호 변경</button></div>
</form>

		</div>
		<div class="col-4">
		</div>
	</div>
	

</div>
</body>
</html>