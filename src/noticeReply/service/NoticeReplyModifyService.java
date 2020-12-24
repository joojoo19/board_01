package noticeReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import noticeReply.dao.NoticeReplyDao;
import noticeReply.model.NoticeReply;


public class NoticeReplyModifyService {

	private NoticeReplyDao dao = new NoticeReplyDao();
	
	public void modify(NoticeReplyModifyRequest reModReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			NoticeReply reply = dao.selectById(con, reModReq.getUserId());
			if(reply == null) {
				throw new NoticeReplyNotFoundException();
			}
			if(!canModify(reModReq.getUserId(), reply)) {
				throw new PermissionDeniedException();
			}
			dao.update(con, reModReq.getReplyid(), reModReq.getBody());
			
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

	private boolean canModify(String modfyingUserId, NoticeReply reply) {
		return reply.getMemberId().equals(modfyingUserId);
	}
}
