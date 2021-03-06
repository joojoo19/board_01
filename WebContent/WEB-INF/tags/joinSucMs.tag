<%@tag import="javax.websocket.SendResult"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<%
		String messageContent = null;
	if (session.getAttribute("messageContent") != null) {
		messageContent = (String) session.getAttribute("messageContent");
	}
	String messageType = null;
	if (session.getAttribute("messageType") != null) {
		messageType = (String) session.getAttribute("messageType");
	}
	if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content"
					<%if (messageType.equals("오류메세지"))
	out.println("panel-warning");
else
	out.println("panel-success");%>>
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
						</h4>
					</div>
					<div class="modal-body"><%= messageContent %></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="location.href='<%= request.getContextPath()%>/login.do';">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('#messageModal').modal("show");


	</script>
	<%
		session.removeAttribute("messageContent");
	session.removeAttribute("messageType");
	}
	
	%>