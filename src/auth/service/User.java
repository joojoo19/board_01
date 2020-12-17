package auth.service;

public class User {
	private String id;
	private String name;
	private String email;
	private String pw;

	public String getPw() {
		return pw;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public User(String id, String name, String email, String pw) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pw = pw;
	}
	
}
