package rest;

public interface UserDao {
	
	public boolean addUser( String userName, String hashPass, String email);
	
	public User getUser(String userName);

}
