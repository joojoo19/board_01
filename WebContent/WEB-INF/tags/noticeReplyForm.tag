<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="noticeNo" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${root }/notice/reply/add.do" method="post">
			<input type="number" value="${ noticeNo}" name="no" hidden /> <input
				type="number" value="${ param.pageNo}" name="pageNo" hidden />
	<div class="form-group">
							<textarea cols="25" rows="4" class="form-control"
								id="input1-content" name="body"  placeholder="댓글을 입력해주세요" ></textarea>
								<div class="text-right mt-2">
									<button type="submit" class="btn btn-primary m-0" style="width: 13%;">등록</button>	
						</div>
				</div>
		</form>
	</div>
</c:if>