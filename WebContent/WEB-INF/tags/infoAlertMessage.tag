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

<div class="alert alert-info" role="alert">
<%= messageContent %>
</div>

	<%
		session.removeAttribute("messageContent");
	session.removeAttribute("messageType");
	}
	
	%>