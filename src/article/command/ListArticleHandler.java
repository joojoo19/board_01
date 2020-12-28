package article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.ListArticleService;
import mvc.command.CommandHandler;
import notice.model.Notice;
import notice.service.ListNoticeArticleService;
import notice.service.NoticePage;

public class ListArticleHandler implements CommandHandler {
	private ListArticleService listService = new ListArticleService();
	private ListNoticeArticleService noticeService = new ListNoticeArticleService();


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		NoticePage noticePage = noticeService.getNoticePage();
		req.setAttribute("articlePage", articlePage);
		req.setAttribute("noticePage", noticePage);
		return "listArticle";
	}
}
