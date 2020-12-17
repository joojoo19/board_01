package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.FindIdService;
import member.service.FindPasswordService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class FindPasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "findPW";
	private FindPasswordService findPwSvc = new FindPasswordService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			if(req.getMethod().equalsIgnoreCase("GET")) {
				return processForm(req, res);
			} else if (req.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(req, res);
			} else {
				res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
		}
		private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
		private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String id = req.getParameter("id");
			
			Map<String, Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			
			if(name == null || name.isEmpty()) {
				errors.put("name", Boolean.TRUE);
			}
			if(email == null || email.isEmpty()) {
				errors.put("email", Boolean.TRUE);
			}
			if(id == null || id.isEmpty()) {
				errors.put("id", Boolean.TRUE);
			}
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			try {
				User user = findPwSvc.FindPw(id, name, email);
				req.getSession().setAttribute("messageType", "성공메세지");
				req.getSession().setAttribute("messageContent", user.getName()+"님의 비밀번호는 [ " + user.getPw() + " ] 입니다");
				return FORM_VIEW;
			} catch (MemberNotFoundException e) {
				e.printStackTrace();
				req.getSession().setAttribute("messageType", "오류메세지");
				req.getSession().setAttribute("messageContent", "회원이 아닙니다. 회원 가입를 해주세요");
				return FORM_VIEW;
			}
		
	}
}
