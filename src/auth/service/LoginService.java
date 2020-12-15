package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.service.MemberNotFoundException;

public class LoginService {
	private MemberDao memberDao = new MemberDao();
	
	public User login(String id, String pw) {
		try (Connection con = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(con, id);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchPassword(pw)) {
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
