package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import notice.dao.NoticeContentDao;
import notice.dao.NoticeDao;
import notice.model.Notice;
import notice.model.NoticeContent;
import jdbc.ConnectionProvider;

public class ReadNoticeService {
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public NoticeData getNotice(int noticeNum, boolean increaseReadCount) {
		try (Connection con = ConnectionProvider.getConnection()){
			Notice notice = noticeDao.selectById(con, noticeNum);
			if(notice == null) {
				throw new NoticeNotFoundException();
			}
			NoticeContent content = contentDao.selectedById(con, noticeNum);
			
			//  이전, 다음 게시물 번호
			int preNum = noticeDao.selectPrePage(con, noticeNum);
			int nextNum = noticeDao.selectNextPage(con, noticeNum);
			if(content == null) {
				throw new NoticeContentNotFoundException();
			}
			if(increaseReadCount) {
				noticeDao.increaseReadCount(con, noticeNum);
			}
			return new NoticeData(notice, content, preNum, nextNum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
