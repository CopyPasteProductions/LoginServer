package rest.domain;

import rest.User;

public class LoginResponse {

boolean success = false;
public boolean isSuccess() {
	return success;
}


private User user;


public LoginResponse(boolean success, User _user) {
	super();
	this.user = _user;
	this.success = success;

}


public User getUser() {
	return user;
}

}
