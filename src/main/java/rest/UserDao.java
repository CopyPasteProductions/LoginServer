package rest;

import rest.domain.GenericResponse;

public interface UserDao {
	
	public GenericResponse login(String userName, String password);
	
	public GenericResponse addUser( String userName, String hashPass, String email);
	
	public User getUser(String userName);

}
