package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import notice.dao.NoticeDao;
import notice.model.Notice;
import jdbc.ConnectionProvider;

public class ListNoticeService {
	private NoticeDao noticeDao = new NoticeDao();
	private int size = 10;
	
	public NoticePage getNoticePage(int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = noticeDao.selectCount(con);
			List<Notice> content = noticeDao.select(con, pageNum, size);
			return new NoticePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
