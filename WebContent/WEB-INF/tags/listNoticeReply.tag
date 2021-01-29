<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ attribute name="noticeNo" type="java.lang.Integer"%>
<%@ attribute name="list" type="java.lang.Integer"%>

<style>
button {
	border: none;
}

li {
	list-style: none;
}

.reply-info {
	color: gray;
}

</style>
<div>

	<div class="text-center">
		댓글&nbsp;
		<c:if test="${size > 0}">[${size }]</c:if>
	</div>


	<c:forEach items="${replyList }" var="reply" varStatus="status">

				<c:if test="${ not empty reply.level }"> 
				<c:forEach begin="1" end="${reply.level }"><ul><li></c:forEach></c:if>
				<div class="reply-info">
					<span style="font-size: 17px; text-align: center;">${reply.memberId }님</span>
					&nbsp;&verbar;&nbsp;<span style="text-align: right;"><fmt:formatDate
							value="${reply.modDate }" pattern="YYYY-MM-DD HH:MS" /> </span></div>
		
					<div class="d-flex bd-highlight">
						<div class="pt-2 w-100 bd-highlight">${reply.body }</div>
						<div class="p-2 flex-shrink-1 bd-highlight">
							<div class="d-flex align-items-end flex-column bd-highlight mb-3">
								<div class="bd-highlight pt-2" style="width: 60px">
									<c:if test="${not empty sessionScope.authUser }">
										<button id="re-reply-btn"
											href="#collapse-reply${status.count }" data-toggle="collapse"
											aria-expanded="false"
											aria-controls="collapse-reply${status.count }">답글</button>
									</c:if>
								</div>
								<c:if test="${authUser.id == reply.memberId }">
									<div class="bd-highlight pt-2" style="width: 60px">
										<button id="modify-btn"
											href="#collapse-modify${status.count }"
											data-toggle="collapse" aria-expanded="false"
											aria-controls="collapse-modify${status.count }">수정</button>
									</div>
									<div class="bd-highlight pt-2" style="width: 60px">
										<form action="${root }/notice/reply/remove.do" method="post"
											id="removeForm">
											<input type="number" name="no" value="${reply.id}" hidden />
											<input type="number" name="pageNo" value="${param.pageNo}"
												hidden /> <input type="number" name="noticeNo"
												value="${reply.noticeNo}" hidden />

											<button type="button" id="remove-btn" onclick="remove();">삭제</button>
										</form>
									</div>
								</c:if>
							</div>
						</div>
						</div>
						<div class="collapse" id="collapse-modify${status.count }">
							<form action="${root }/notice/reply/modify.do" method="post">
								<input type="number" name="no" value="${reply.id}" hidden /> <input
									type="number" name="pageNo" value="${param.pageNo}" hidden />
								<input type="number" name="noticeNo"
									value="${reply.noticeNo}" hidden />
								<textarea cols="25" rows="4" class="form-control"
									id="input1-content" name="body">${reply.body }</textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0">등록</button>
								</div>
							</form>
						</div>

						<div class="collapse" id="collapse-reply${status.count }">
							<form action="${root }/notice/reply/add.do" method="post">
								<input type="number" name="no" value="${reply.id}" hidden /> <input
									type="number" name="pageNo" value="${param.pageNo}" hidden />
								<input type="number" name="noticeNo"
									value="${reply.noticeNo}" hidden />
								<textarea cols="25" rows="4" class="form-control" name="body"
									placeholder="댓글을 입력해주세요"></textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0">등록</button>
								</div>
							</form>
						</div>

						<hr />
							<c:if test="${ not empty reply.level }"> 
				<c:forEach begin="1" end="${reply.level }"></li></ul></c:forEach></c:if>
			</li>
		</ul>


	</c:forEach>


</div>
<script>
	function remove() {
		if (confirm("삭제하시겠습니까?") == true) {
			document.getElementById('removeForm').submit();
		} else {
			return;
		}
	};
</script>
