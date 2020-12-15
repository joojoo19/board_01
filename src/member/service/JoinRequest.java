package member.service;

import java.util.Map;

public class JoinRequest {
	private String id;
	private String name;
	private String pw;
	private String confirmPw;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

	
	public void validate(Map<String, Boolean> errors) {
		// id값이 잘 들어왔는지?
		checkEmpty(errors, id, "id");
		// name 잘 들어왔는지?
		checkEmpty(errors, name, "name");
		// password 잘 들어왔는지?
		checkEmpty(errors, pw, "pw");
		// confirmPw 잘 들어왔는지?
		checkEmpty(errors, confirmPw, "confirmPw");
		checkEmpty(errors, email, "email");
		
		if (!errors.containsKey("confirmPw")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", true);
			}
		}
	}
	
	public boolean isPasswordEqualToConfirm() {
		return pw != null && pw.equals(confirmPw);
	}
	
	private void checkEmpty(Map<String, Boolean> errors,
			String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, true);
		}
	}
	
}
