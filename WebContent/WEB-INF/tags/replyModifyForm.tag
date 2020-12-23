<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function() {
	$("#modify-btn").click(function() {
		$("#reply-modify").removeAttr("hidden");
		$("#reply-modify").toggle();
	});
	$("#remove-btn").click(function() {
		var c = confirm("삭제하시겠습니까?");
		if(c) {
			location.href="remove?idx=${param.idx}";	
		}
	});
});
</script>
		<form action="${root }/reply/modify.do" method="post" hidden>	
<button id="modify-btn">수정</button>
	<button type="submit" id="remove-btn" name="cmd" value="remove">삭제</button>

						<tr id="reply-modify" hidden>
						<td colspan="3">

			<input type="number" value="${reply.id}" name="modiNo" hidden /> <input
				type="number" value="${param.pageNo}" name="pageNo" hidden />
				<div class="form-group">
							<textarea cols="25" rows="4" class="form-control"
								id="input1-content" name="body" >${reply.body }</textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0" name="cmd" value="modify" style="width: 13%;">등록</button>	
						</div>
				</div>


						</td>
				</tr>
				</form>