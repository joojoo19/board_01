<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

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
<link rel="stylesheet" href="css/custom.css">

<title>회원 정보</title>
</head>
<body>
	<u:navbar />
	<div class="container">




		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h3>회원 정보</h3>
				<table class="table table-borderless">
					<tbody>
						<tr>
							<td><div class="card"
									style="border-color: #608df9; height: 130px;">
									<div class="card-body text-center">
										  <h4 class="card-title">내 정보 변경</h4>
										 <h6 class="card-subtitle mb-2 text-muted">이름, 아이디, 이메일 변경</h6>
										 <a href="${root }/changeInfo.do" class="card-link"><button type="submit" class="btn btn-primary btn-sm">수정</button></a>
									</div>
								</div></td>
						</tr>
						<tr>
							<td><div class="card"
									style="border-color: #608df9;height: 130px;">
									<div class="card-body text-center ">
									<h4 class="mb-3">비밀번호 변경</h4>
												 <a href="${root }/changePwd.do" class="card-link"><button type="submit" class="btn btn-primary btn-sm  mt-3">변경</button></a>
												 </div>
								</div></td>
						</tr>
						<tr>
							<td><div class="card"
									style="border-color: #608df9; height: 130px;">
									<div class="card-body text-center pt-4 pb-3"><h4>회원 탈퇴</h4>
												 <a href="${root }/removeMember.do" class="card-link"><button type="submit" class="btn btn-primary btn-sm mt-3">탈퇴</button></a></div>
								</div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
</body>
</html>