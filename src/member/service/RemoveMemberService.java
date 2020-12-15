package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveMemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void removeMember(User user, String pw) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member member = memberDao.selectById(con, user.getId());
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchPassword(pw)) {
				throw new InvalidPasswordException();
			}
			memberDao.delete(con, member);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	} 
}
