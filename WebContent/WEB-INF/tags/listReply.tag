
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ attribute name="articleNo" type="java.lang.Integer"%>
<%@ attribute name="list" type="java.lang.Integer"%>


<style>
button {
	border: none;
}

</style>
<div>


	<table class="table table-sm">
		<thead>

			<tr>

				<th colspan="4" class="text-center">댓글&nbsp;<c:if test="${size > 0}">[${size }]</c:if></th>

			</tr>

		</thead>
		</table>
		<table class="table table-borderless">
		<c:forEach items="${replyList }" var="reply" varStatus="status">
			<tbody>
				<tr>
					<td><span style="font-size: 20px; text-align: center;">${reply.memberId }님
					</span></td>
					<td colspan="2"><div class="reply-body">
						<c:forEach begin="1" end="${reply.level-1 }">${"ㄴ" }</c:forEach>
							${reply.body }
						</div> <fmt:formatDate value="${reply.modDate }"
							pattern="YYYY-M-D HH:mm" /></td>
				<td>
		<div class="text-right mb-2">
		<c:if test="${not empty sessionScope.authUser }">
					<button id="re-reply-btn" href="#collapse-reply${status.count }" data-toggle="collapse"						
							aria-expanded="false" aria-controls="collapse-reply${status.count }">답글</button> </c:if>

									
							<c:if test="${authUser.id == reply.memberId }">
							<button id="modify-btn" href="#collapse-modify${status.count }" data-toggle="collapse"						
							aria-expanded="false" aria-controls="collapse-modify${status.count }">수정</button>
							<form action="${root }/reply/remove.do" method="post" id="removeForm">
								<input type="number" name="no" value="${reply.id}" hidden />
								<input type="number" name="pageNo" value="${param.pageNo}"
									hidden /> <input type="number" name="articleNo"
									value="${reply.articleNum}" hidden />
								<button type="button" id="remove-btn" onclick="remove();">삭제</button>
							</form></c:if>
						</div></td>
				</tr>
				<tr>
					<td colspan="4">

						<div class="collapse" id="collapse-modify${status.count }">
							<form action="${root }/reply/modify.do" method="post">
								<input type="number" name="no" value="${reply.id}" hidden />
								<input type="number" name="pageNo" value="${param.pageNo}"
									hidden /> <input type="number" name="articleNo"
									value="${reply.articleNum}" hidden />
								<textarea cols="25" rows="4" class="form-control"
									id="input1-content" name="body">${reply.body }</textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0" name="cmd"
										value="modify">등록</button>
								</div>
							</form>
						</div>


					</td>
				</tr>
					<tr>
					<td colspan="4">

						<div class="collapse" id="collapse-reply${status.count }">
							<form action="${root }/reply/add.do" method="post">
								<input type="number" name="no" value="${reply.id}" hidden />
								<input type="number" name="pageNo" value="${param.pageNo}"
									hidden /> <input type="number" name="articleNo"
									value="${reply.articleNum}" hidden />
								<textarea cols="25" rows="4" class="form-control"
									 name="body" placeholder="댓글을 입력해주세요" ></textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0" name="cmd"
										value="modify">등록</button>
								</div>
							</form>
						</div>


					</td>
				</tr>

			</tbody>

		</c:forEach>
	</table>

</div>
<script>

function remove() {
	if(confirm("삭제하시겠습니까?") == true) {      
		document.getElementById('removeForm').submit();
	} else {
		 return;	
	}
};

</script>