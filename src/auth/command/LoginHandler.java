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
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return null;
		} catch (LoginFailException e) {
			e.printStackTrace();
			errors.put("pwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}catch (MemberNotFoundException e) {
			e.printStackTrace();
			errors.put("idNotMatch", Boolean.TRUE);
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
