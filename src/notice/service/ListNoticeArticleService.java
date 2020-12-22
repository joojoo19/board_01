package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import notice.dao.NoticeDao;
import notice.model.Notice;
import jdbc.ConnectionProvider;

public class ListNoticeArticleService {
	private NoticeDao noticeDao = new NoticeDao();
	private int size = 2;
	
	public NoticePage getNoticePage() {
		try (Connection con = ConnectionProvider.getConnection()){
			List<Notice> content = noticeDao.select(con, 1, size);
			return new NoticePage(content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
