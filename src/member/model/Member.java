package member.model;

import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String pw;
	private String email;
	private Date regDate;

	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

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
	public boolean matchPassword(String pwd) {
		return pw.equals(pwd);
	}
	public boolean matchName(String name) {
		return this.name.equals(name);
	}
	public boolean matchEmail(String email) {
		return this.email.equals(email);
	}
	public boolean matchId(String id) {
		return this.id.equals(id);
	}
	
	public void changePassword(String newPwd) {
		this.pw = newPwd;
	}
	public void changeName(String newName) {
		this.name = newName;
	}
	public void changeEmail(String newEmail) {
		this.email = newEmail;
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
	
	
}
