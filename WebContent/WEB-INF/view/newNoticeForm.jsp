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
<title>Insert title here</title>
</head>
<body>
	<u:navbar />
	<div class="container">

		<div class="row">
			<div class="col-3">
			</div>
			<div class="col-6">

					<h3>글쓰기</h3>
					<br />
					<form action="${root}/notice/write.do" method="post">
						<div class="form-group">
							<label for="input1-title">제목</label> <input type="text"
								class="form-control" id="input1-title" name="title"
								value="${param.title}">
						</div>

						<div class="form-group">
							<label for="input2-content">내용</label>
							<textarea cols="30" rows="9" class="form-control"
								id="input2-content" name="content">${param.content } </textarea>
						</div>


						<div class="text-right">
							<button type="submit" class="btn btn-primary">등록</button>
								<a href="${root }/notice/list.do"><button type="button" class="btn btn-primary">취소</button></a>
						</div>
					</form>

			</div>
			<div class="col-3">

			</div>
		</div>

	</div>
	<u:newNoticeMs/>
	<u:copyright/>
</body>
</html>