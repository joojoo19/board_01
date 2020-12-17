package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;

public class PrePageArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			Article article = articleDao.selectNextPage(con, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			ArticleContent content = contentDao.selectedById(con, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}

			return new ArticleData(article, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
