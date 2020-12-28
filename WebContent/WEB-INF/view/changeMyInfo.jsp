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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet" href="css/custom.css">

<title>joojoo's Home</title>
<script>
	
</script>
</head>
<body>
	<u:navbar />
	<div class="container">
		<div class="row">
			<div class="col-4"></div>


			<div class="col-4">
				<h3>${authUser.name }님의정보 변경</h3>
				<br />
				<u:infoAlertMessage />
				<form action="${root}/changeMyInfo.do" method="post" id="form">
					<div class="form-group">
						<label for="input1-name">이름</label><input type="text"
							class="form-control" id="input1-name" name="name"
							value="${param.name}" style="border-color: #608df9">
						<!-- 						<div class="text-right">
							<button type="button" class="btn btn-primary mt-2" style="width: 100%"
								name="cmd" value ="1" id="changeName-btn">변경</button> 
						</div> -->

					</div>
					<div class="form-group">
						<label for="input2-email">이메일</label> <input type="text"
							class="form-control" id="input2-email" name="email"
							value="${param.email}" style="border-color: #608df9">
						<div class="text-right">
							<button type="submit" class="btn btn-primary mt-2"
								style="width: 100%">변경</button>
						</div>

					</div>
				</form>

			</div>
			<div class="col-4"></div>
		</div>
	</div>
	<u:copyright />

</body>
</html>

