package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class RemoveArticleService2 {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void removeArticle(int removeNo) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Article article = articleDao.selectById(con, removeNo);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canRemove(article.getWriter().getId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.delete(con, article.getNumber());
			contentDao.delete(con, article.getNumber());
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}

	private boolean canRemove(String userId, Article article) {
		return article.getWriter().getId().equals(userId);
	}
}

