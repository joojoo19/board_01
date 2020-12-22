package noticeReply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import mvc.command.CommandHandler;
import noticeReply.service.NoticeReplyAddService;


public class NoticeReplyAddHandler implements CommandHandler {
	private NoticeReplyAddService addService = new NoticeReplyAddService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");
		
		int noticeNo = Integer.parseInt(req.getParameter("no"));
		String userId = user.getId();
		String body = req.getParameter("body");
		addService.add(userId, noticeNo, body);
		
		res.sendRedirect(req.getContextPath() + "/notice/read.do?no=" + noticeNo);
		
		return null;
	}

}
