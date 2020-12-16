package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class RemoveArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void remove(ModifyRequest modReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Article article = articleDao.selectById(con, modReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canModify(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			articleDao.delete(con, modReq.getArticleNumber());
			contentDao.delete(con, modReq.getArticleNumber());
			
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

	private boolean canModify(String modfyingUserId, Article article) {
		return article.getWriter().getId().equals(modfyingUserId);
	}
}
