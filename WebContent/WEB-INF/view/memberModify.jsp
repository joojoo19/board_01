<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<style>
.container {
	margin-top: 10%;
}

.box-border {
	border: solid 1px #608df9;
	margin-bottom: 6px;
	width: 200px;
	height: 35px;
}

.btn-modify {
	border: solid 1px #608df9;
	background-color: #608df9;
	margin-bottom: 6px;
	color: white;
	width: 70px;
	height: 35px;
}

.box-border-pw {
	border: solid 1px #608df9;
	margin-bottom: 6px;
	width: 272px;
	height: 35px;
}

.btn-submit {
	background-color: #608df9;
	border: solid 1px #608df9;
	color: white;
	width: 100px;
	height: 40px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<table>
								<thead>
					<tr>
						<th colspan="3" style="text-align: center"><h4>회원 정보 수정</h4><br /></th>
					</tr>
				</thead>
			<tr>
				<td style="width: 80px;">이름</td>
				<td><input type="text" class="box-border" id="name" /></td>
				<td><button class="btn-modify">수정</button></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="email" class="box-border" id="email" /></td>
				<td><button class="btn-modify">수정</button></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td colspan="2"><input type="password" class="box-border-pw"
					id="curPw" placeholder="현재 비밀번호"/></td>
			<tr>
				<td></td>
				<td colspan="2"><input type="password" class="box-border-pw"
					id="newPw" placeholder="새 비밀번호"/></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"><input type="password" class="box-border-pw"
					id="newPwconfirm" placeholder="새 비밀번호 확인"/></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2" style="text-align: right"><button type="submit"
						class="btn-submit">확인</button></td>
			<tr>
		</table>
	</div>
</body>
</html>