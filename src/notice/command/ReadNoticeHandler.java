package notice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import notice.service.NoticeContentNotFoundException;
import notice.service.NoticeData;
import notice.service.NoticeNotFoundException;
import notice.service.ReadNoticeService;
import noticeReply.model.NoticeReply;
import noticeReply.service.NoticeReplyService;

public class ReadNoticeHandler implements CommandHandler {
	private ReadNoticeService readService = new ReadNoticeService();
	private NoticeReplyService replyService = new NoticeReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int noticeNum = Integer.parseInt(noVal);
		try {
			NoticeData noticeData = readService.getNotice(noticeNum, true);
			List<NoticeReply> noticerReplyList = replyService.getReplyList(noticeNum); 
			req.setAttribute("noticeData", noticeData);
			req.setAttribute("noticerReplyList", noticerReplyList);
			return "readNotice";
		} catch (NoticeNotFoundException | NoticeContentNotFoundException e) {
//			req.getServletContext().log("no article", e);
			System.out.println("게시물이 없습니다.");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
