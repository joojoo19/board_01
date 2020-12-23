package reply.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.PermissionDeniedException;
import reply.service.ReplyNotFoundException;
import reply.service.ReplyRemoveService;

public class ReplyRemoveHandler implements CommandHandler {
	ReplyRemoveService reRemoveSvc = new ReplyRemoveService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");

		int no = Integer.valueOf(req.getParameter("modiNo"));
		System.out.println(no);
		int articleNo = Integer.valueOf(req.getParameter("no"));
		System.out.println(articleNo);
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		System.out.println(pageNo);

		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (!errors.isEmpty()) {
			return null;
		}
			
			try {
				reRemoveSvc.remove(no, authUser);
				res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
				return null;
			} catch (ReplyNotFoundException e) {
				e.printStackTrace();
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return  null;
			} catch (PermissionDeniedException e) {
				e.printStackTrace();
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return  null;
			}
		
		}
		
	}

