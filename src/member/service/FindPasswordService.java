package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class FindPasswordService {
private MemberDao memberDao = new MemberDao();
	
	public User FindPw(String id, String name, String email) {
		try (Connection con = ConnectionProvider.getConnection()){
			Member member = memberDao.selectByIdNNameNEmail(con, id, name, email);
			if(member == null) {
				throw new MemberNotFoundException();
			}	
			
			return new User(member.getId(), member.getName(), member.getEmail(), member.getPw());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
