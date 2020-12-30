package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.ReplyAddService;

public class ReplyAddHandler implements CommandHandler {
	private ReplyAddService addService = new ReplyAddService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");
		String no = req.getParameter("no");
		if(no == null || no.isEmpty()) {
			no = "0";
		}
		System.out.println("no : " + no);
		int replyNo = Integer.parseInt(no);
		System.out.println(replyNo);
		int groupNo = Integer.parseInt(no);				
		if(groupNo == 0) {
			groupNo = 1;
		}
		System.out.println(groupNo);
		int articleNo = Integer.parseInt(req.getParameter("articleNo"));
		System.out.println(articleNo);
		String userId = user.getId();
		String body = req.getParameter("body");
		System.out.println(body);
		addService.add(userId, articleNo, body, replyNo, groupNo);
		
		res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
		
		return null;
	}

}
