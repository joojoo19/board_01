<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h3>회원 정보 변경</h3>
			<br />
<form action="${root }/changeMyInfo.do" method="post">
<u:alertMessage/>
 <div class="form-group">
    <label for="input1-name">이름</label>
    <input type="text" class="form-control" id="input1-name" name="name" style="border-color: #608df9">
  </div>
  <div class="form-group">
    <label for="input2-email">이메일</label>
    <input type="email" class="form-control" id="input2-email" name="email" style="border-color: #608df9">
  </div>
		<div class="text-right"><button type="submit" class="btn btn-primary">정보 변경</button></div>
</form>

		</div>
		<div class="col-4">
		</div>
	</div>
	

</div>
</body>
</html>