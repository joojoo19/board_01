package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.FindIdService;
import member.service.MemberNotFoundException;
import member.service.NotMatchEmailException;
import member.service.NotMatchNameException;
import mvc.command.CommandHandler;


public class FindIdHandler implements CommandHandler {
	private static final String FORM_VIEW = "findID";
	private FindIdService findIdSvc = new FindIdService();
	
	
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
			
			Map<String, Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			
			if(name == null || name.isEmpty()) {
				errors.put("name", Boolean.TRUE);
			}
			if(email == null || email.isEmpty()) {
				errors.put("email", Boolean.TRUE);
			}
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			try {
				User user = findIdSvc.FindId(name, email);
				req.getSession().setAttribute("messageType", "성공메세지");
				req.getSession().setAttribute("messageContent", user.getName()+"님의 ID는 [ " + user.getId() + " ] 입니다");
				return FORM_VIEW;
			} catch (MemberNotFoundException e) {
				e.printStackTrace();
				req.getSession().setAttribute("messageType", "오류메세지");
				req.getSession().setAttribute("messageContent", "ID를 찾을 수 없습니다");
				return FORM_VIEW;
			} 
	}

}
