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
		String no = req.getParameter("no");
		if(no == null || no.isEmpty()) {
			no = "0";
		}
		int replyNo = Integer.parseInt(no);
		int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
		int groupNo = Integer.parseInt(no);				
		if(groupNo == 0) {
			groupNo = 1;
		}
		String userId = user.getId();
		String body = req.getParameter("body");
		addService.add(userId, noticeNo, body, replyNo, groupNo);
		
		res.sendRedirect(req.getContextPath() + "/notice/read.do?no=" + noticeNo);
		
		return null;
	}

}
