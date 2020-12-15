package member.model;

import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String pw;
	private String email;
	private Date birth;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPw() {
		return pw;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirth() {
		return birth;
	}
	public boolean matchPassword(String pwd) {
		return pw.equals(pwd);
	}
	
	public void changePassword(String newPwd) {
		this.pw = newPwd;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}
