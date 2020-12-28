package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.SearchArticleService;
import mvc.command.CommandHandler;
import notice.service.ListNoticeArticleService;
import notice.service.NoticePage;

public class SearchArticleHandler implements CommandHandler {
	private SearchArticleService searchSvc = new SearchArticleService();

	/*
	 * private ListNoticeArticleService noticeService = new
	 * ListNoticeArticleService();
	 */
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// list.do?search=title&keyword=공지
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
		
		ArticlePage articlePage = null;
		if(field.equals("content")) {
			articlePage = searchSvc.getArticlePage(keyword, pageNo);
			req.setAttribute("articlePage", articlePage);
			return "listArticle";
		}
		 articlePage = searchSvc.getArticlePage(field, keyword, pageNo);
		/* NoticePage noticePage = noticeService.getNoticePage(); */
		req.setAttribute("articlePage", articlePage);
		/* req.setAttribute("noticePage", noticePage); */
		return "listArticle";
	}

}