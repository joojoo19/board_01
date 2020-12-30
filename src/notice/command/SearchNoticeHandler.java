package notice.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.ArticlePage;
import mvc.command.CommandHandler;
import notice.service.NoticePage;
import notice.service.SearchNoticeService;

public class SearchNoticeHandler implements CommandHandler {
	private SearchNoticeService searchNotiSvc = new SearchNoticeService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		String field_ = req.getParameter("search");
		String keyword_ = req.getParameter("keyword");
		System.out.println(field_ +","+ keyword_);
		String field = "title";
		String keyword = "";

		if(field_ != null) {
			field = field_;
		}
		if(keyword_ != null) {
			keyword = keyword_;
		}
		System.out.println(field +","+keyword);
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		try {
		NoticePage noticePage = null;
		if(field.equals("content")) {
			noticePage = searchNotiSvc.getArticlePage(keyword, pageNo);
			req.setAttribute("noticePage", noticePage);
			return "listArticle";
		}
		 noticePage = searchNotiSvc.getArticlePage(field, keyword, pageNo);
		/* NoticePage noticePage = noticeService.getNoticePage(); */
		req.setAttribute("noticePage", noticePage);
		/* req.setAttribute("noticePage", noticePage); */
		return "listArticle"; 
		}catch (ArticleNotFoundException e) {
			errors.put("searchFail", true);
			return "listArticle";
		}
	}


}
