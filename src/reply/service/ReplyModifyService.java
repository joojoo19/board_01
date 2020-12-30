package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;


public class ReplyModifyService {

	private ReplyDao dao = new ReplyDao();
	
	public void modify(ReplyModifyRequest reModReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Reply reply = dao.selectById(con, reModReq.getId());
			if(reply == null) {
				throw new ReplyNotFoundException();
			}
			if(!canModify(reModReq.getUserId(), reply)) {
				throw new PermissionDeniedException();
			}
			dao.update(con, reModReq.getId(), reModReq.getBody());
			
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

	private boolean canModify(String modfyingUserId, Reply reply) {
		return reply.getMemberId().equals(modfyingUserId);
	}
}
