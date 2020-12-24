<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<script>
	function removeNotice() {
		if (confirm("삭제하시겠습니까?") == true) {
			document.getElementById('removeNotice-form').submit();
		} else {
			return;
		}
	};
</script>
<body>
	<u:navbar />

	<div class="container">
		<br />
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h3>공지글</h3>
				<br />
				<div class="text-right mb-2">

					<a href="${root }/notice/read.do?no=${noticeData.prevNumber}"><button
							type="button" id="btn-prev" class="btn btn-primary btn-sm"
							<c:if test="${ noticeData.prevNumber eq 0 }">
										disabled
									</c:if>>이전글</button></a>

					<a href="${root }/notice/read.do?no=${noticeData.nextNumber}"><button
							type="button" id="btn-next" class="btn btn-primary btn-sm"
							<c:if test="${ noticeData.nextNumber eq 0 }">
										disabled
									</c:if>>다음글</button></a>
				</div>
				<table class="table table-bordered table-sm">
					<tbody>
						<tr>
							<td width="15%">번호</td>
							<td width="35%">${noticeData.notice.number }</td>
							<td width="15%">작성일</td>
							<td width="35%"><fmt:formatDate
									value="${noticeData.notice.regDate }"
									pattern="yyyy-MM-dd hh:mm" /></td>
						</tr>

						<tr>
							<td width="15%">작성자</td>
							<td width="35%">${noticeData.notice.writer.name }</td>
							<td width="15%">수정일</td>
							<td width="35%"><fmt:formatDate
									value="${noticeData.notice.modDate }"
									pattern="yyyy-MM-dd  hh:mm" /></td>
						</tr>
						<tr>
							<td colspan="4" class="text-center">내용</td>
						</tr>
						<tr>
							<td>제목</td>
							<td colspan="3"><c:out value="${noticeData.notice.title }"></c:out></td>
						</tr>
						<tr>
							<td colspan="4">
								<div style="height: 250px; overflow:scroll;"><u:pre value="${noticeData.content }"/></div>
							</td>

						</tr>
					</tbody>
				</table>

				<div class="d-flex bd-highlight mb-3">
					<div class="mr-auto bd-highlight">

						<c:set var="pageNo"
							value="${empty param.pageNo ? '1' : param.pageNo }" />
						<a href="${root }/article/list.do?pageNo=${pageNo }"><button
								type="submit" class="btn btn-primary">목록</button></a>
					</div>

					<c:if test="${authUser.id == noticeData.notice.writer.id }">
						<div class="pr-1 bd-highlight">
							<a
								href="${root }/notice/modify.do?no=${noticeData.notice.number }"><button
									type="submit" class="btn btn-primary">수정</button></a>
						</div>

						<form action="${root }/article/remove.do" id="removeNotice-form"
							method="POST">
							<input type="text" name="removeNo"
								value="${noticeData.notice.number }" hidden />
							<div class="bd-highlight">
								<button type="button" class="btn btn-primary"
									onclick="removeNotice();">삭제</button>

							</div>
						</form>
					</c:if>
				</div>
				<hr />
				<u:listNoticeReply />
				<hr />
				<div class="mb-3">
					<u:noticeReplyForm noticeNo="${noticeData.notice.number }" />
				</div>


				<div class="col-3"></div>
			</div>

		</div>

	</div>
</body>
</html>