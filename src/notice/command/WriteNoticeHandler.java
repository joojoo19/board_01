package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import auth.service.*;
import notice.service.*;
import notice.model.*;
import mvc.command.CommandHandler;

public class WriteNoticeHandler implements CommandHandler {
	private static final String FORM_VIEW = "newNoticeForm";
	private WriteNoticeService writeService = new WriteNoticeService();

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
	Map<String, Boolean> errors = new HashMap<>();
	req.setAttribute("errors", errors);
	
	User user = (User) req.getSession(false).getAttribute("admin");
	WriteRequest writeReq = createWriteRequest(user, req);
	writeReq.validate(errors);
	
	if(!errors.isEmpty()) {
		return FORM_VIEW;
	}
	
	int newNoticeNO = writeService.write(writeReq);
	req.setAttribute("newNoticeNo", newNoticeNO);
	
	try {
		res.sendRedirect(req.getContextPath()+"/notice/list.do");
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getName()), req.getParameter("title"), req.getParameter("content"));
}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
