<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="articleNo" type="java.lang.Integer"%>

<div class="card" id="comments">
  <div class="card-body">
    <!-- 댓글 작성 창 -->
	<form action="${root }/reply/add.do" method="post">
  <div class="form-group">
  
    <label>댓글 작성</label>
    <textarea class="form-control" id="comment-content" rows="3"></textarea>
  </div>
			<input type="number" value="${ articleNo}" name="no" hidden /> 
			<input type="number" value="${ param.pageNo}" name="pageNo" hidden />
  <button type="button" class="btn btn-primary" id="comment-create-btn">제출</button>
</form>
    <!-- 댓글 목록 -->
   <ul class="list-unstyled">

    <li class="media mt-4" id="comment-{{id}}">
      <img src="https://api.adorable.io/avatars/64/{{author}}.png" class="mr-3" alt="avata">
      <div class="media-body">
        <!-- 댓글 -->
        <h5 class="mt-0 mb-1">
          <small>
            <!-- 부트스트랩 collapse 활용, https://getbootstrap.com/docs/4.5/components/collapse/ -->
            <a href="#" class="comment-edit-btn" data-toggle="collapse" data-target=".multi-collapse-{{id}}">수정</a>
          </small>
        </h5>
        <!-- 보기 모드 -->
        <p class="collapse multi-collapse-{{id}} show"></p>
        <!-- 수정 모드 -->
        <form class="collapse multi-collapse-{{id}}">
          <div class="form-group">
            <textarea class="form-control" id="comment-content" rows="3"></textarea>
          </div>
          <input type="hidden" id="comment-id" value="{{id}}">
          <input type="hidden" id="comment-author" value="{{author}}">
          <button type="button" class="btn btn-info comment-update-btn">수정 완료</button>
        </form>
      </div>
    </li>
</ul>
  </div>
</div>
<Script src="/js/app/comment.js"></script>