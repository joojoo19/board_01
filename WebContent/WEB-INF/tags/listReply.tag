
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ attribute name="articleNo" type="java.lang.Integer"%>
<script>
	$(function() {
		$(".reply-modify").hide();
		$(".modify-btn").click(function() {
			$(".reply-modify").toggle();
		});
		
		$("#remove-btn").click(function() {
			var c = confirm("삭제하시겠습니까?");
			if (c) {
				location.href = "remove?idx=${param.idx}";
			}
		});
	});
</script>
<style>
button {
	border: none;
}
</style>
<div>


	<table class="table table-sm">
		<thead>
			<tr>
				<th colspan="3" class="text-center">댓글</th>
			</tr>
		</thead>
		<c:forEach items="${replyList }" var="reply" varStatus="status">
			<tbody>
				<tr>
					<td><span style="font-size: 20px; text-align: center;">${reply.memberId }님
					</span></td>
					<td><div class="reply-body">
							<p>${reply.body }</p>
						</div> <fmt:formatDate value="${reply.regDate }"
							pattern="yyyy-mm-dd HH:mm" /></td>
					<td><c:if test="${authUser.id == reply.memberId }">

							<button class="modify-btn">수정</button>
							<form action="${root }/reply/remove.do" method="post">
								<input type="number" name="modiNo" value="${reply.id}" hidden />
								<input type="number" name="pageNo" value="${param.pageNo}"
									hidden /> <input type="number" name="no"
									value="${reply.articleNum}" hidden />
								<button type="submit" id="remove-btn" name="cmd" value="remove">삭제</button>
							</form>
						</c:if></td>
				</tr>
				<tr>
					<td colspan="3">

						<div class="form-group reply-modify">
							<form action="${root }/reply/modify.do" method="post">
								<input type="number" name="modiNo" value="${reply.id}" hidden />
								<input type="number" name="pageNo" value="${param.pageNo}"
									hidden /> <input type="number" name="no"
									value="${reply.articleNum}" hidden />
								<textarea cols="25" rows="4" class="form-control"
									id="input1-content" name="body">${reply.body }</textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0" name="cmd"
										value="modify" style="width: 13%;">등록</button>
								</div>
							</form>
						</div>


					</td>
				</tr>
			</tbody>

		</c:forEach>
	</table>

</div>
