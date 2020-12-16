package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil;
import oracle.net.aso.c;

public class ArticleContentDao {
	public int delete(Connection con, int removeNo) throws SQLException {
		String sql = "DELETE article_content WHERE article_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, removeNo);
			return pstmt.executeUpdate();
		}
	}
	public int update(Connection con, int no, String content) throws SQLException {
		String sql = "UPDATE article_content SET content = ? WHERE article_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public ArticleContent selectedById(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article_content WHERE article_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;
			if (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public ArticleContent insert(Connection con, ArticleContent content) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO article_content (article_no, content) " + "VALUES (?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}

	}
}
