package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangeInfoService;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangeInfoHandler implements CommandHandler {
	private static final String FORM_VIEW = "changeMyInfo";
	private ChangeInfoService changeInfoSvc = new ChangeInfoService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String name = req.getParameter("name");
		System.out.println("name : " + name);
		String email = req.getParameter("email");
		System.out.println("email : " + email);

		try {
			if (!name.isEmpty() && !email.isEmpty()) {
				changeInfoSvc.changeName(user.getId(), name);
				changeInfoSvc.changeEmail(user.getId(), email);
				req.getSession().setAttribute("messageType", "정보변경성공메세지");
				req.getSession().setAttribute("messageContent", "이름과 이메일이 변경되었습니다");
			} else if (!name.isEmpty() && email.isEmpty()) {
				changeInfoSvc.changeName(user.getId(), name);
				req.getSession().setAttribute("messageType", "이름성공메세지");
				req.getSession().setAttribute("messageContent", "이름이 변경되었습니다");
			}
			else if (name.isEmpty() && !email.isEmpty()) {
				changeInfoSvc.changeEmail(user.getId(), email);
				req.getSession().setAttribute("messageType", "이메일성공메세지");
				req.getSession().setAttribute("messageContent", "이메일이 변경되었습니다");
			}

			return FORM_VIEW;
		} 	catch (NullPointerException e) {
			return FORM_VIEW;
		}
		catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

	}

}
