package notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.service.NoticeData;
import notice.service.NoticeNotFoundException;
import notice.service.ModifyNoticeService;
import notice.service.ModifyRequest;
import notice.service.PermissionDeniedException;
import notice.service.ReadNoticeService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyNoticeHandler implements CommandHandler {
	private static final String FORM_VIEW = "noticeModifyForm";
	
	private ReadNoticeService readService = new ReadNoticeService();
	private ModifyNoticeService modifyService = new ModifyNoticeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("admin");
		String noVal = req.getParameter("no");
		int no = Integer.valueOf(noVal);
		int pageNum = Integer.valueOf(req.getParameter("pageNo"));

		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			res.sendRedirect(req.getContextPath() + "/notice/read.do?no=" + no +"&page=" + pageNum);
			return null;
		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			NoticeData noticeData = readService.getNotice(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser"); 
			if(!canModify(authUser, noticeData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, noticeData.getNotice().getTitle(), noticeData.getContent());
			req.setAttribute("modReq", modReq);
					return FORM_VIEW;	
		} catch (NoticeNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	
	}

	private boolean canModify(User authUser, NoticeData noticeData) {
		String writerId = noticeData.getNotice().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
}
