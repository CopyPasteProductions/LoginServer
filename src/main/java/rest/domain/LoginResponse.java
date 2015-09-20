package rest.domain;

import rest.User;

public class LoginResponse {
String userName = "HelloWorld";
private User user;


public LoginResponse(String h1) {
	super();
	this.userName = h1;
}
public LoginResponse(User _user) {
	super();
	this.user = _user;

}


public User getUser() {
	return user;
}

}
