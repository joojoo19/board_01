package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;

public class ListArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = articleDao.selectCount(con);
			List<Article> content = articleDao.select(con, pageNum, size);
			for(Article a : content) {
				System.out.println( "service : " + a.getTitle());
			}
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
