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
			<div class="col-3"></div>
			<div class="col-6">

				<h3>게시글 수정</h3>
				<br />
				<form action="${root }/notice/modify.do" method="POST">
					<table class="table table-bordered table-sm">
						<tbody>
							<tr>
								<input type="text" name="no" value="${modReq.articleNumber}"
									hidden />
								<td width="15%">번호</td>
								<td width="85%">${modReq.articleNumber}</td>
							</tr>
							<tr>
								<td><label for="input2-title">제목</label></td>
								<td colspan="3"><input type="text" class="form-control"
									id="input2-title" name="title" value="${modReq.title}"></td>
							</tr>
							<tr>
								<td colspan="4" class="text-center"><label
									for="input3-content">내용</label></td>
							</tr>
							<tr>
								<td colspan="4"><textarea cols="30" rows="9"
										class="form-control" id="input3-content" name="content">${modReq.content } </textarea>

								</td>

							</tr>
						</tbody>
					</table>

					<c:set var="pageNo"
						value="${empty param.pageNo ? '1' : param.pageNo }" />
					<div class="text-right">
						<button type="button" class="btn btn-primary" id=""
							onclick="$('#myModal').modal('show')">수정</button>
						<a href="${root }/article/list.do"><button type="button"
								class="btn btn-primary">취소</button></a>
					</div>
					<div class="modal" id="myModal" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Modal title</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<p>수정을 완료 하시겠습니까?</p>
								</div>
								<div class="modal-footer">

									<button type="submit" class="btn btn-primary">확인</button>

									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">취소</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="col-3"></div>
		</div>

	</div>
	<u:copyright />

</body>
</html>

