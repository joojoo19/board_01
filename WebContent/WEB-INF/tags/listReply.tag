<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>


	<table class="table table-sm">
		<thead>
			<tr>
				<th width="15%" class="text-center">ID</th>
				<th colspan="3" class="text-center">댓글</th>
			</tr>
		</thead>
		<c:forEach items="${replyList }" var="reply">
			<tbody>
				<tr>
					<td rowspan="2" class="text-center">${reply.memberId }</td>
					<td rowspan="2" colspan="3" width="85%">${reply.body }</td>

				</tr>
				<tr>
					<td colspan="2" class="text-right">
					<div class="d-flex justify-content-end"><fmt:formatDate
							value="${reply.regDate }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${authUser.id == reply.memberId }">
				
							<div class="pr-1 bd-highlight">
								<a href="${root }/replyModify.do?no=${reply.id }">수정</a>
							</div>


							<div class="bd-highlight">
								<a href="${root }/replyRemove.do?no=${reply.id }">
								삭제
								</a>
							</div></c:if>
</div>
						</td>

				</tr>
			</tbody>
		</c:forEach>
	</table>
</div>