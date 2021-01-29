package noticeReply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import noticeReply.model.NoticeReply;
import reply.model.Reply;

public class NoticeReplyDao {
	public int delete(Connection con, int no) throws SQLException {
		String sql = "DELETE notice_reply WHERE id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int update(Connection con, int no, String body) throws SQLException {
		String sql = "UPDATE notice_reply SET body = ?, moddate = SYSDATE WHERE id = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	
	public void insert(Connection conn, String userId, int noticeNo, String body, int replyNo, int groupNo) throws SQLException {

		String sql = "INSERT INTO notice_reply "
				+ "(replyid, groupid, memberid, notice_no, body, regdate, moddate) "
				+ "VALUES (?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, replyNo);
			pstmt.setInt(2, groupNo);
			pstmt.setString(3, userId);
			pstmt.setInt(4, noticeNo);
			pstmt.setString(5, body);
			
			pstmt.executeUpdate();
		}
	}

	public List<NoticeReply> listReply(Connection con, int noticeNum) throws SQLException {
		String sql = "SELECT * FROM (SELECT ROWNUM r, data.* FROM  " + 
				"(SELECT LEVEL, id, replyid, groupid, memberid, notice_no, body, regdate, moddate FROM notice_reply " + 
				"START WITH replyid = 0 CONNECT BY PRIOR id = replyid ORDER SIBLINGS BY groupid DESC) data) " + 
				"WHERE notice_no =?";

		List<NoticeReply> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, noticeNum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeReply r = new NoticeReply();
				r.setLevel(rs.getInt(2));
				r.setId(rs.getInt(3));
				r.setReplyId(rs.getInt(4));
				r.setGroupId(rs.getInt(5));
				r.setMemberId(rs.getString(6));
				r.setNoticeNo(rs.getInt(7));
				r.setBody(rs.getString(8));
				r.setRegDate(rs.getTimestamp(9));
				r.setModDate(rs.getTimestamp(10));
				
				list.add(r);
			}
		}
		return list;
	}

	public NoticeReply selectById(Connection con, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice_reply WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			NoticeReply reply = null;
			if (rs.next()) {
				reply = convertReply(rs);
			}
			return reply;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	
	}
	private NoticeReply convertReply(ResultSet rs) throws SQLException {
		return new NoticeReply(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getTimestamp(7), rs.getTimestamp(8));
	
	}
	
}
