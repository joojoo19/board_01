<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>


<div class="container">
<div class="row">
	<div class="col-3"></div>
	<div class="col-6">
	<form action="${root }/article/remove.do" method="POST">
   <input type="text" name="no" id="" value="${param.no }" hidden/>
   <div style="height: 200px"></div>
   <div style="border: 1px solid gray; height: 200px">
	<div style="text-align: center" class="mt-5">
       <h4 style="align: center">삭제 하시겠습니까?</h4>
       <br />
        <button type="submit" class="btn btn-primary">확인</button>
         <a href="${root }/article/read.do?no=${param.no }"><button type="button" class="btn btn-secondary">취소</button></a>
	</div></div>
</form>
	</div>
	<div class="col-3"></div>
</div>

</div>
</body>
</html>