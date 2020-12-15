<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="value" type="java.lang.String" required="true" %>

<%

value = value.replace("&", "&amp;");
value = value.replace("<", "&lt;");
value = value.replace(">", "&rt;");
value = value.replace("\n", "\n<br>");
value = value.replace(" ", "&nbsp;");
%>
<%= value %>