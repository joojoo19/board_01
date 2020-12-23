package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyRemoveService {
	private ReplyDao dao = new ReplyDao();
	public void remove(int no, User authUser) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			Reply reply = dao.selectById(con, authUser.getId());

			if(!reply.getMemberId().equals(authUser.getId())) {
				throw new PermissionDeniedException();
			}
			dao.delete(con, no);
			
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
}
