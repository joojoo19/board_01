package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import notice.model.Notice;
import notice.model.NoticeContent;
import jdbc.JdbcUtil;
import oracle.net.aso.c;

public class NoticeContentDao {
	public int[] search(Connection con, String keyword) throws SQLException {
	String sql = "SELECT notice_no FROM notice_content WHERE content LIKE ? ORDER BY notice_no DESC";
	try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		pstmt.setString(1,  "%"+ keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		

		int[] listNo = new int[count(con, keyword)];
		int i = 0;
		while (rs.next()) {
			listNo[i] = rs.getInt(1);
			System.out.println(listNo[i]);
			i++;
		}
		return listNo;
	}
}
public int count(Connection con, String keyword) throws SQLException {
	String sql = "SELECT COUNT(*) FROM notice_content WHERE content LIKE ?";
	try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		pstmt.setString(1, "%"+ keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		int i = 0;
		if(rs.next()) {
		i = rs.getInt(1); }
		System.out.println("i : "+i);
		return i;
	}
}
	public int delete(Connection con, int removeNo) throws SQLException {
		String sql = "DELETE notice_content WHERE notice_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, removeNo);
			return pstmt.executeUpdate();
		}
	}
	public int update(Connection con, int no, String content) throws SQLException {
		String sql = "UPDATE notice_content SET content = ? WHERE notice_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public NoticeContent selectedById(Connection con, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice_content WHERE notice_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			NoticeContent content = null;
			if (rs.next()) {
				content = new NoticeContent(rs.getInt("notice_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public NoticeContent insert(Connection con, NoticeContent content) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO notice_content (notice_no, content) " + "VALUES (?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}

	}
}
