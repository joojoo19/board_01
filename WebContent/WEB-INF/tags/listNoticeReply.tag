<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
									<td class="text-center">${reply.memberId }</td>
					<td colspan="3" width="85%">${reply.body }</td>

				</tr>
			</tbody>
		</c:forEach>
	</table>
</div>