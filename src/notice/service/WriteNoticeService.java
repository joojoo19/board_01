package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import notice.dao.NoticeContentDao;
import notice.dao.NoticeDao;
import notice.model.Notice;
import notice.model.NoticeContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteNoticeService {
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public Integer write(WriteRequest req) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Notice notice = toNotice(req);
			Notice savedNotice = noticeDao.insert(con, notice);
			if(savedNotice == null) {
				throw new RuntimeException("fail to insert article");
			}
			NoticeContent content = new NoticeContent(savedNotice.getNumber(), req.getContent());
			NoticeContent savedContent = contentDao.insert(con, content);

			if(savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			con.commit();
			
			return savedNotice.getNumber();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(con);
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}

	private Notice toNotice(WriteRequest req) {
		return new Notice(null, req.getWriter(), req.getTitle(), null, null, 0);
	}


}
