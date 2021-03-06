package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.service.SameAsBeforeException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler {
	private static final String FORM_VIEW = "changePwdForm";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPwd = req.getParameter("curPwd");
		System.out.println(curPwd);
		String newPwd = req.getParameter("newPwd");
		System.out.println(newPwd);
		String confirmNewPwd = req.getParameter("confirmNewPwd");
		System.out.println(confirmNewPwd);
		
		if (curPwd == null || curPwd.isEmpty()) {
			errors.put("curPwd", true);
		}
		
		if (newPwd == null || newPwd.isEmpty()) {
			errors.put("newPwd", true);
		}
		if (confirmNewPwd == null || confirmNewPwd.isEmpty()) {
			errors.put("confirmNewPwd", true);
		}		
		if (!newPwd.equals(confirmNewPwd)) {
			errors.put("notMatchconfirmPwd", true);
		}
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			changePwdSvc.changePassword(user.getId(), curPwd, newPwd);
			req.getSession().setAttribute("messageType", "성공메세지");
			req.getSession().setAttribute("messageContent", "비밀번호가 변경되었습니다");
			return FORM_VIEW;
		} catch (InvalidPasswordException e) {
			errors.put("badCurPwd", true);
			return FORM_VIEW;
		}	catch (SameAsBeforeException e) {
			e.printStackTrace();
			errors.put("badNewPwd", true);
			return FORM_VIEW;
		} 
		catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
	}
	
	
}
