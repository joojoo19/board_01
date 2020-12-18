package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import article.service.RemoveArticleService;
import article.service.RemoveArticleService2;
import auth.service.User;
import mvc.command.CommandHandler;

public class RemoveArticleHandler implements CommandHandler{
	
	private ReadArticleService readService = new ReadArticleService();
	private RemoveArticleService removeArticleService = new RemoveArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		// 현재 로그인 한 사용자
		// 삭제하려는 게시물의 작성자
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		ArticleData articleData = readService.getArticle(no, false);
		
		// 가 같으면 삭제함
		//    암호가 일치하는 지 확인 해서
		//           일치하면 삭제
		//           아니면 throw exception
		
		// 안 같으면 throw exception
		if (!authUser.getId().equals(articleData.getArticle().getWriter().getId())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		try {
			removeArticleService.delete(no, authUser);
		
		} catch (PermissionDeniedException e) {
			errors.put("invalidePassword", true);
			return "listArticle";
		}	catch (Exception e) {
			throw new RuntimeException(e);
		}
			return "listArticle";
		}
	}

