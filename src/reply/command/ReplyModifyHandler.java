package reply.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.PermissionDeniedException;
import reply.service.ReplyModifyRequest;
import reply.service.ReplyModifyService;
import reply.service.ReplyNotFoundException;
import reply.service.ReplyService;

public class ReplyModifyHandler implements CommandHandler {
	private ReplyModifyService modifyService = new ReplyModifyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		int no = Integer.valueOf(req.getParameter("modiNo"));
		System.out.println(no);
		int articleNo = Integer.valueOf(req.getParameter("no"));
		System.out.println(articleNo);
		String body = req.getParameter("body");
		System.out.println(body);
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		System.out.println(pageNo);
		

		
		
		ReplyModifyRequest reModReq = new ReplyModifyRequest(no, authUser.getId(), body);
		req.setAttribute("reModReq", reModReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		reModReq.validate(errors);
		if (!errors.isEmpty()) {
			return null;
		}

			try {
				modifyService.modify(reModReq);
				res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
				return null;
			} catch (ReplyNotFoundException e) {
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
