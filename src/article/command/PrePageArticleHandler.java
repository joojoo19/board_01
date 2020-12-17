package article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.PrePageArticleService;
import article.service.ReadArticleService;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReplyService;

public class PrePageArticleHandler implements CommandHandler {
	private PrePageArticleService prePageSvc = new PrePageArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try {
			ArticleData articleData = prePageSvc.getArticle(articleNum);
			
			req.setAttribute("articlePrePage", articleData);
			return "readArticle";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
//			req.getServletContext().log("no article", e);
			System.out.println("게시물이 없습니다.");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
