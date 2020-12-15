package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "joinForm";
	private JoinService joinService = new JoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {

		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPw(req.getParameter("pw"));
		joinReq.setConfirmPw(req.getParameter("confirmPw"));
		joinReq.setEmail(req.getParameter("email"));

		
		 Map<String, Boolean> errors = new HashMap<>(); 
		 req.setAttribute("errors",errors);
		
		 joinReq.validate(errors);
		 
		 if(!errors.isEmpty()) { 
		 return FORM_VIEW; 
		 } 
		 
			try {
				joinService.join(joinReq);
				req.getSession().setAttribute("messageType", "성공메세지");
				req.getSession().setAttribute("messageContent", "가입이 되었습니다");
				return "loginForm";
			} catch (DuplicateIdException e) {
				errors.put("duplicateId", true);
				return FORM_VIEW;
			}
		 
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

}
