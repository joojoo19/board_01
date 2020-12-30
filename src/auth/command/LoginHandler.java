package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "loginForm";
	private LoginService loginService = new LoginService();
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
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = trim(req.getParameter("id"));
		String pw = trim(req.getParameter("pw"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(id == null || id.isEmpty()) {
			errors.put("id", Boolean.TRUE);
		}
		if(pw == null || pw.isEmpty()) {
			errors.put("pw", Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			User user = loginService.login(id, pw);
			req.getSession().setAttribute("authUser", user);  // public은 session으로 받음.
			req.getSession().setAttribute("messageType", "성공메세지");
			req.getSession().setAttribute("messageContent", user.getName()+"님 반갑습니다.");
			if(user.getId().equals("admin")) {
				req.getSession().setAttribute("admin", user);
			}
			return FORM_VIEW;
		} catch (LoginFailException e) {
			e.printStackTrace();
			req.getSession().setAttribute("messageType", "에러메세지");
			req.getSession().setAttribute("messageContent", "비밀번호가  맞지 않습니다");
			return FORM_VIEW;
		}catch (MemberNotFoundException e) {
			e.printStackTrace();
			req.getSession().setAttribute("messageType", "에러메세지");
			req.getSession().setAttribute("messageContent", "아이디가 맞지 않습니다");
			return FORM_VIEW;
		}
	}
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
