package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {
	public Member selectByIdNNameNEmail(Connection con, String id, String name, String email) throws SQLException {

		Member member = null;

		String sql = "SELECT memberid, name, password, email, regdate " + "FROM member " + "WHERE memberid=? AND name=? AND email=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setRegDate(rs.getTimestamp(5));

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return member;
	}
	
	public Member selectByNameNEmail(Connection con, String name, String email) throws SQLException {

		Member member = null;

		String sql = "SELECT memberid, name, password, email, regdate " + "FROM member " + "WHERE name=? AND email=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setRegDate(rs.getTimestamp(5));

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}

		return member;
	}

	public Member selectById(Connection con, String id) throws SQLException {

		Member member = null;

		String sql = "SELECT memberid, name, password, email, regdate " + "FROM member " + "WHERE memberid=?";

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
				member.setRegDate(rs.getTimestamp(5));

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
		String sql = "INSERT INTO member " + "(memberid, name, password, email, regdate) " + "VALUES (?, ?, ?, ?, SYSDATE) ";

		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getEmail());

			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection con, Member member) throws SQLException {
		String sql = "UPDATE member SET name=?, password=?, email=? WHERE memberid=?";
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void delete(Connection con, Member member) throws SQLException {
		String sql = "DELETE member WHERE memberid=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
