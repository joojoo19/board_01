package noticeReply.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import noticeReply.dao.NoticeReplyDao;
import noticeReply.model.NoticeReply;

public class NoticeReplyService {
	private NoticeReplyDao dao = new NoticeReplyDao();
	
	public List<NoticeReply> getReplyList(int noticeNum) {
		Connection con = ConnectionProvider.getConnection();
		
		List<NoticeReply> list;
		try {
			list = dao.listReply(con, noticeNum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return list;
	}
	
}
