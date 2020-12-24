package noticeReply.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import noticeReply.model.NoticeReply;
import noticeReply.service.PermissionDeniedException;
import noticeReply.service.NoticeReplyModifyRequest;
import noticeReply.service.NoticeReplyModifyService;
import noticeReply.service.NoticeReplyNotFoundException;
import noticeReply.service.NoticeReplyService;

public class NoticeReplyModifyHandler implements CommandHandler {
	private NoticeReplyModifyService modifyService = new NoticeReplyModifyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		int no = Integer.valueOf(req.getParameter("modiNo"));
		System.out.println(no);
		int articleNo = Integer.valueOf(req.getParameter("no"));
		System.out.println(articleNo);
		String body = req.getParameter("body");
		System.out.println(body);

		

		
		
		NoticeReplyModifyRequest reModReq = new NoticeReplyModifyRequest(no, authUser.getId(), body);
		req.setAttribute("reModReq", reModReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		reModReq.validate(errors);
		if (!errors.isEmpty()) {
			return null;
		}

			try {
				modifyService.modify(reModReq);
				res.sendRedirect(req.getContextPath() + "/notice/read.do?no=" + articleNo);
				return null;
			} catch (NoticeReplyNotFoundException e) {
				e.printStackTrace();
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} catch (PermissionDeniedException e) {
				e.printStackTrace();
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return  null;
			}
		/*
		 * res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
		 */

	}

}
