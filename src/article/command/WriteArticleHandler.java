package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import auth.service.*;
import article.service.*;
import article.model.*;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "newArticleForm";
	private WriteArticleService writeService = new WriteArticleService();

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
	
	User user = (User) req.getSession(false).getAttribute("authUser");
	WriteRequest writeReq = createWriteRequest(user, req);
	writeReq.validate(errors);
	
	if(!errors.isEmpty()) {
		return FORM_VIEW;
	}
	
	int newArticleNO = writeService.write(writeReq);
	req.setAttribute("newArticleNo", newArticleNO);
	req.getSession().setAttribute("messageType", "성공메세지");
	req.getSession().setAttribute("messageContent", "글이 등록되었습니다.");
	return FORM_VIEW;
}
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getName()), req.getParameter("title"), req.getParameter("content"));
}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}
