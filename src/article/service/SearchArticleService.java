package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;

public class SearchArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	int size = 10;
	
	public ArticlePage getArticlePage(String field, String keyword, int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = articleDao.selectCount(con, field, keyword);
			List<Article> content = articleDao.select(con, field, keyword);

			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public ArticlePage getArticlePage(String keyword, int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = contentDao.count(con, keyword);
			int [] articleNo = contentDao.search(con, keyword);
			List<Article> content = null;
			List<Article> list = new ArrayList<Article>();
			for(int i : articleNo) {
				Article a =  articleDao.selectById(con, i);
				list.add(a);
				System.out.println("searchSvc i : " + i);				
			}
			content = list;
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
