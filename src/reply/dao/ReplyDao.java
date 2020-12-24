package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import notice.model.Notice;
import notice.model.Writer;
import reply.model.Reply;

public class ReplyDao {
	public int delete(Connection con, int no) throws SQLException {
		String sql = "DELETE reply WHERE replyid = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int update(Connection con, int no, String body) throws SQLException {
		String sql = "UPDATE reply SET body = ?, regdate = SYSDATE WHERE replyid = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	

	public void insert(Connection conn, String userId, int articleNo, String body) throws SQLException {
		// 11g
		/*
		String sql = "INSERT INTO reply "
				+ "(replyid, memberid, article_no, body, regdate) "
				+ "VALUES (reply_seq.nextval, ?, ?, ?, SYSDATE)";
		*/
		
		String sql = "INSERT INTO reply "
				+ "(memberid, article_no, body, regdate, moddate) "
				+ "VALUES (?, ?, ?, SYSDATE, SYSDATE)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, articleNo);
			pstmt.setString(3, body);
			
			pstmt.executeUpdate();
		}
	}

	public List<Reply> listReply(Connection con, int articleNum) throws SQLException {
		String sql = "SELECT replyid,"
				+ " memberid,"
				+ " article_no,"
				+ " body,"
				+ " regdate, moddate " + 
				"FROM reply " + 
				"WHERE article_no=? " + 
				"ORDER BY replyid";

		List<Reply> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, articleNum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply r = new Reply();
				r.setId(rs.getInt(1));
				r.setMemberId(rs.getString(2));
				r.setArticleNum(rs.getInt(3));
				r.setBody(rs.getString(4));
				r.setRegDate(rs.getTimestamp(5));
				r.setModDate(rs.getTimestamp(6));
				
				list.add(r);
			}
		}
		return list;
	}

	public Reply selectById(Connection con, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reply WHERE memberid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Reply reply = null;
			if (rs.next()) {
				reply = convertReply(rs);
			}
			return reply;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}

	}
	private Reply convertReply(ResultSet rs) throws SQLException {
		return new Reply(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5));

	}
}
