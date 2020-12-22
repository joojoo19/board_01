package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import notice.dao.NoticeContentDao;
import notice.dao.NoticeDao;
import notice.model.Notice;
import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveNoticeService {
	private MemberDao memberDao = new MemberDao();
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao noticeContentDao = new NoticeContentDao();

	public void delete(int no, User authUser) {

		Connection con = ConnectionProvider.getConnection();
		try {
			con.setAutoCommit(false);
			Member member = memberDao.selectById(con, authUser.getId());
			
			// 같지 않으면 throw exception
			if (!member.getId().equals(authUser.getId())) {
				throw new PermissionDeniedException();
			}
			
			// password와 사용자의 비번이 같으면
			//   articleDao.delete, articleContentDao.delete
			noticeDao.delete(con, no);
			noticeContentDao.delete(con, no);
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		}
		
	}
}
