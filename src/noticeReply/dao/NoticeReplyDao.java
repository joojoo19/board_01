package noticeReply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import noticeReply.model.NoticeReply;

public class NoticeReplyDao {

	public void insert(Connection conn, String userId, int noticeNo, String body) throws SQLException {
		// 11g
		/*
		String sql = "INSERT INTO reply "
				+ "(replyid, memberid, article_no, body, regdate) "
				+ "VALUES (reply_seq.nextval, ?, ?, ?, SYSDATE)";
		*/
		
		String sql = "INSERT INTO notice_reply "
				+ "(memberid, notice_no, body, regdate, moddate) "
				+ "VALUES (?, ?, ?, SYSDATE, SYSDATE)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, noticeNo);
			pstmt.setString(3, body);
			
			pstmt.executeUpdate();
		}
	}

	public List<NoticeReply> listReply(Connection con, int noticeNum) throws SQLException {
		String sql = "SELECT replyid,"
				+ " memberid,"
				+ " notice_no,"
				+ " body,"
				+ " regdate, moddate " + 
				"FROM notice_reply " + 
				"WHERE notice_no=? " + 
				"ORDER BY replyid DESC";

		List<NoticeReply> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, noticeNum);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeReply r = new NoticeReply();
				r.setId(rs.getInt(1));
				r.setMemberId(rs.getString(2));
				r.setArticleNum(rs.getInt(3));
				r.setBody(rs.getString(4));
				r.setRegDate(rs.getTimestamp(5));
				r.setRegDate(rs.getTimestamp(6));
				
				list.add(r);
			}
		}
		return list;
	}
	
}
