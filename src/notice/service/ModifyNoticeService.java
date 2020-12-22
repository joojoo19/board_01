package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import notice.dao.NoticeContentDao;
import notice.dao.NoticeDao;
import notice.model.Notice;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ModifyNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Notice notice = noticeDao.selectById(con, modReq.getArticleNumber());
			if(notice == null) {
				throw new NoticeNotFoundException();
			}
			if(!canModify(modReq.getUserId(), notice)) {
				throw new PermissionDeniedException();
			}
			noticeDao.update(con, modReq.getArticleNumber(), modReq.getTitle());
			contentDao.update(con, modReq.getArticleNumber(), modReq.getContent());
			
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

	private boolean canModify(String modfyingUserId, Notice notice) {
		return notice.getWriter().getId().equals(modfyingUserId);
	}
}
