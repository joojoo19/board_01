package noticeReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import noticeReply.dao.NoticeReplyDao;

public class NoticeReplyAddService {
	private NoticeReplyDao dao = new NoticeReplyDao();

	public void add(String userId, int noticeNo, String body, int replyNo, int groupNo) {
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			dao.insert(conn, userId, noticeNo, body, replyNo, groupNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
}
