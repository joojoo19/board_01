<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="noticeNo" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${root }/notice/reply/add.do" method="post">
			<input type="number" value="${ noticeNo}" name="no" hidden /> <input
				type="number" value="${ param.pageNo}" name="pageNo" hidden />
			<div class="form-group form-inline m-0 p-0">
<input type="text" class="form-control m-0 p-0" name="body" placeholder="댓글을 입력해주세요" style="width: 87%;"/>


			<button type="submit" class="btn btn-primary m-0" style="width: 13%;">등록</button>			</div>
		</form>
	</div>
</c:if>