package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;

public class ReadArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try (Connection con = ConnectionProvider.getConnection()){
			Article article = articleDao.selectById(con, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			ArticleContent content = contentDao.selectedById(con, articleNum);
			
			//  이전, 다음 게시물 번호
			int preNum = articleDao.selectPrePage(con, articleNum);
			int nextNum = articleDao.selectNextPage(con, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			if(increaseReadCount) {
				articleDao.increaseReadCount(con, articleNum);
			}
			return new ArticleData(article, content, preNum, nextNum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
