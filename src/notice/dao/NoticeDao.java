package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import notice.model.Notice;
import notice.model.Writer;
import jdbc.JdbcUtil;
import member.model.Member;

public class NoticeDao {
	public int selectPrePage(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT notice_no FROM notice WHERE notice_no = (SELECT max(notice_no) FROM notice WHERE notice_no < ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			int result = 0;
			if (rs.next()) {
				result = rs.getInt(1);
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	public int selectNextPage(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT notice_no FROM notice WHERE notice_no = (SELECT min(notice_no) FROM notice WHERE notice_no > ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			int result = 0;
			if (rs.next()) {
				result = rs.getInt(1);
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public int delete(Connection con, int removeNo) throws SQLException {
		String sql = "DELETE notice WHERE notice_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, removeNo);
			return pstmt.executeUpdate();
		}
	}
	
	public int update(Connection con, int no, String title) throws SQLException {
		String sql = "UPDATE notice SET title = ?, moddate = SYSDATE WHERE notice_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	public Notice selectById(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice WHERE notice_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Notice notice = null;
			if (rs.next()) {
				notice = convertNotice(rs);
			}
			return notice;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}

	}

	public void increaseReadCount(Connection con, int no) throws SQLException {
		String sql = "UPDATE notice SET read_cnt = read_cnt + 1 WHERE notice_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int selectCount(Connection con) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM notice");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Notice> select(Connection con, int pageNum, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT rn, notice_no, writer_id, writer_name, title, regdate, moddate, read_cnt, reply_cnt "
				+ "FROM (SELECT notice_no, writer_id, writer_name, title, regdate, moddate, read_cnt, reply_cnt, ROW_NUMBER() "
				+ "OVER (ORDER BY notice_no DESC) rn FROM notice_view) " + " WHERE rn Between ? and ?"; // sql develop��
																										// 1踰좎씠�뒪
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (pageNum - 1) * size + 1); // 1 5 : 1 ~ 5 ...... x n : (x-1)*n +1 ~ x*n
			pstmt.setInt(2, pageNum * size);

			rs = pstmt.executeQuery();
			List<Notice> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertNoticeReply(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Notice convertNotice(ResultSet rs) throws SQLException {
		return new Notice(rs.getInt("notice_no"), new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), rs.getTimestamp("regdate"), rs.getTimestamp("moddate"), rs.getInt("read_cnt"));

	}
	private Notice convertNoticeReply(ResultSet rs) throws SQLException {
		return new Notice(rs.getInt("notice_no"), new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), rs.getTimestamp("regdate"), rs.getTimestamp("moddate"), rs.getInt("read_cnt"), rs.getInt("reply_cnt"));

	}
	public Notice insert(Connection con, Notice notice) throws SQLException {
		String sql = "INSERT INTO notice " + "(writer_id, writer_name, title," + " regdate, moddate, read_cnt) "
				+ "VALUES (?, ?, ?, SYSDATE, SYSDATE, 0)";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql, new String[] { "notice_no", "regdate", "moddate" });

			pstmt.setString(1, notice.getWriter().getId());
			pstmt.setString(2, notice.getWriter().getName());
			pstmt.setString(3, notice.getTitle());

			int cnt = pstmt.executeUpdate();

			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				if (rs.next()) {
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Notice(key, notice.getWriter(), notice.getTitle(), regDate, modDate, 0);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}
