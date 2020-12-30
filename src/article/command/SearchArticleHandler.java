package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.ArticlePage;
import article.service.SearchArticleService;
import member.service.InvalidPasswordException;
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
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
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
		
		try {
		ArticlePage articlePage = null;
		if(field.equals("content")) {
			articlePage = searchSvc.getArticlePage(keyword, pageNo);
			req.setAttribute("articlePage", articlePage);

			return "listArticle";
		}
		 articlePage = searchSvc.getArticlePage(field, keyword, pageNo);
		req.setAttribute("articlePage", articlePage);

		return "listArticle";
		} catch (ArticleNotFoundException e) {
			errors.put("searchFail", true);
			return "listArticle";
		}
	}

}