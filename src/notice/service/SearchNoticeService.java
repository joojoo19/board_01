package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import notice.dao.NoticeContentDao;
import notice.dao.NoticeDao;
import notice.model.Notice;

public class SearchNoticeService {
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	int size = 10;
	
	public NoticePage getArticlePage(String field, String keyword, int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = noticeDao.selectCount(con, field, keyword);
			List<Notice> content = noticeDao.select(con, field, keyword);

			return new NoticePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public NoticePage getArticlePage(String keyword, int pageNum) {
		try (Connection con = ConnectionProvider.getConnection()){
			int total = contentDao.count(con, keyword);
			int [] articleNo = contentDao.search(con, keyword);
			List<Notice> content = null;
			List<Notice> list = new ArrayList<Notice>();
			for(int i : articleNo) {
				Notice n =  noticeDao.selectById(con, i);
				list.add(n);
				System.out.println("searchSvc i : " + i);				
			}
			content = list;
			return new NoticePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
