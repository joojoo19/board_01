package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import notice.model.Notice;
import notice.model.Writer;
import reply.model.Reply;

public class ReplyDao {
	public int delete(Connection con, int no) throws SQLException {
		String sql = "DELETE reply WHERE id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}

	public int update(Connection con, int no, String body) throws SQLException {
		String sql = "UPDATE reply SET body = ?, moddate = SYSDATE WHERE id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	public void insert(Connection conn, String userId, int articleNo, String body, int replyNo, int groupNo) throws SQLException {

		String sql = "INSERT INTO reply (replyid, groupid, memberid, article_no, body, regdate, moddate) "
				+ "VALUES (?, ?, ?, ?, ?, SYSDATE, SYSDATE)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, replyNo);
			pstmt.setInt(2, groupNo);
			pstmt.setString(3, userId);
			pstmt.setInt(4, articleNo);
			pstmt.setString(5, body);

			pstmt.executeUpdate();
		}
	}

	public List<Reply> listReply(Connection con, int articleNum) throws SQLException {
		String sql = "SELECT * FROM (SELECT ROWNUM r, data.* FROM " + 
				"(SELECT LEVEL, id, replyid, groupid, memberid, article_no, body, regdate, moddate FROM reply " + 
				"START WITH replyid = 0 CONNECT BY PRIOR id = replyid ORDER SIBLINGS BY groupid DESC) data) " + 
				"WHERE article_no =?";

		List<Reply> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, articleNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply r = new Reply();
				r.setLevel(rs.getInt(2));
				r.setId(rs.getInt(3));
				r.setReplyId(rs.getInt(4));
				r.setGroupId(rs.getInt(5));
				r.setMemberId(rs.getString(6));
				r.setArticleNum(rs.getInt(7));
				r.setBody(rs.getString(8));
				r.setRegDate(rs.getTimestamp(9));
				r.setModDate(rs.getTimestamp(10));

				list.add(r);
			}
		}
		return list;
	}
	public Reply selectById(Connection con, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reply WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
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
		return new Reply(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getTimestamp(7), rs.getTimestamp(8));

	}

}
