<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<div class="container mb-3" >
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${root }/index.jsp">joojoo's home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="${root }/index.jsp"><i class="fas fa-home"></i>&nbsp;Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${root }/article/list.do"><i class="fas fa-bars"></i>&nbsp;Board</a>
      </li>

   </ul>
   <u:isLogin>
    <ul class="navbar-nav">
   	<li class="nav-item"><a href="${root }/myinfo.jsp" class="nav-link">Myinfo</a></li>
   	<li class="nav-item"><a href="${root }/logout.do" class="nav-link">Logout</a></li>
   </ul>
   </u:isLogin>
   <u:notLogin>
   <ul class="navbar-nav">
   	<li class="nav-item"><a href="${root }/join.do" class="nav-link">Join</a></li>
   	<li class="nav-item"><a href="${root }/login.do" class="nav-link">Login</a></li>
   </ul>
  </u:notLogin>
  </div>
</nav>
</div>