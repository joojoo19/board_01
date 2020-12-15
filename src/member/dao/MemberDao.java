package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	public Member selectById(Connection con, String id) throws SQLException {

		Member member = null;

		String sql = "SELECT id, name, password, email, birth " + "FROM member " + "WHERE id=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setBirth(rs.getTimestamp(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return member;
	}

	public void insert(Connection con, Member member) throws SQLException {
		String sql = "INSERT INTO member " + "(id, name, password, email, birth) " + "VALUES (?, ?, ?, ?, ?) ";

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getEmail());
			pstmt.setTimestamp(5, (Timestamp) member.getBirth());

			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
}
