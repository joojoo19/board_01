package notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.service.NoticeContentNotFoundException;
import notice.service.NoticeData;
import notice.service.NoticeNotFoundException;
import notice.service.ModifyRequest;
import notice.service.PermissionDeniedException;
import notice.service.ReadNoticeService;
import notice.service.RemoveNoticeService;
import auth.service.User;
import mvc.command.CommandHandler;

public class RemoveNoticeHandler implements CommandHandler{
	private static final String FORM_VIEW = "removeNoticeForm";
	private ReadNoticeService readService = new ReadNoticeService();
	private RemoveNoticeService removeArticleService = new RemoveNoticeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
		private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
		private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		// 현재 로그인 한 사용자
		// 삭제하려는 게시물의 작성자
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("admin");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		NoticeData noticeData = readService.getNotice(no, false);
		
		// 가 같으면 삭제함
		//    암호가 일치하는 지 확인 해서
		//           일치하면 삭제
		//           아니면 throw exception
		
		// 안 같으면 throw exception
		if (!authUser.getId().equals(noticeData.getNotice().getWriter().getId())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		try {
			removeArticleService.delete(no, authUser);
		} catch (PermissionDeniedException e) {
			errors.put("invalidePassword", true);
			return FORM_VIEW;
		}	catch (Exception e) {
			throw new RuntimeException(e);
		}
		res.sendRedirect(req.getContextPath() + "/notice/list.do");
		return null;
		}
	}

