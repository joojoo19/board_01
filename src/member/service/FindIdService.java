package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class FindIdService {
private MemberDao memberDao = new MemberDao();
	
	public User FindId(String name, String email) {
		try (Connection con = ConnectionProvider.getConnection()){
			Member member = memberDao.selectByNameNEmail(con, name, email);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			
			return new User(member.getId(), member.getName(), member.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
