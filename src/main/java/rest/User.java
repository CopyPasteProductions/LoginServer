package rest;

public class User {
	
	
	private int userId;
	
	private String userName;
	
	private String hashedPassword;
	
	private String email;
	
	private boolean validUser;
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public User(int userId, String userName, String hashedPassword, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.hashedPassword = hashedPassword;
		this.email = email;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public String getEmail() {
		return email;
	}


}
