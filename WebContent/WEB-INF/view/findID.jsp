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

<title>아이디 찾기</title>
</head>
<body>
<div class="container" align="center">
<h4>아이디 찾기</h4><br />
<form action="${root }/findid.do" method="post">
<div class="box-title"><p>이름</p></div>
<input type="text" name="name" class="box-border" /><br />
<div class="box-title"><p>이메일</p></div>
<input type="email" name="email" class="box-border" /><br />
<button type="submit" class="btn-submit" >아이디 찾기</button></form>
</div>

</body>
</html>