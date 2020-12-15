package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.dao.MemberDao;
import member.model.Member;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.service.RemoveMemberService;
import mvc.command.CommandHandler;

public class RemoveMemberHandler implements CommandHandler {
	private static final String FORM_VIEW = "removeForm";
	private RemoveMemberService removMemberSvc = new RemoveMemberService();
	
	 @Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			if(req.getMethod().equalsIgnoreCase("GET")) {
				return processForm(req, res);
			} else if(req.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(req, res);
			} else {
				res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		User user = (User) req.getSession().getAttribute("authUser"); 	// 세션에서 user객체 얻기(authUser attr)
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);    // errors맵
		String pw = req.getParameter("pw");	// pw파라미터 얻기
		
		// pw가 null이거나 비어있으면(empty) errors에 코드와 ture 리턴 view리턴
		if(pw == null || pw.isEmpty()) {
			errors.put("emptyPw", Boolean.TRUE);
			return FORM_VIEW;
		}
     					
		// 서비스 일시키기
		try {
		removMemberSvc.removeMember(user, pw);
		return "removeSuccess";
		// 세션을 invalide시키는
	
	} catch (InvalidPasswordException e) {
		// 2. 비밀번호 오류 catch문 2개
		e.printStackTrace();
		errors.put("badPw", Boolean.TRUE);
		return FORM_VIEW;
	} catch (MemberNotFoundException e) {
		// 1. 사용자가 없는 경우
		e.printStackTrace();
		errors.put("notMember", Boolean.TRUE);
		return null;
	} 

		// 에러맵에 추가
		// view리턴

	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
