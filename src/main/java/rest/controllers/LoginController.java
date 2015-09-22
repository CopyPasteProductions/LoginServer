package rest.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.User;
import rest.UserDaoImpl;
import rest.domain.LoginResponse;

@RestController
public class LoginController {

@RequestMapping("/login")
public LoginResponse root(String a)
{
	return new LoginResponse(a);
}


@RequestMapping("/login/getUser")
public LoginResponse test2(String userName)
{
	User u = new UserDaoImpl().getUser(userName);
	boolean success = true;
	success =  (u.getUserName() != null);
	return new LoginResponse(u);
}

/*
@RequestMapping("/login/addUser")
public String addUser(@RequestBody User user)
{
	System.out.println(user.getUserName());
	boolean success = new UserDaoImpl().addUser(user.getUserName(), user.getHashedPassword(), user.getEmail());
	
	if (success)
	{
		return "User Added";
	}
	else
	{
		return "Failed";
	}
}
*/
@RequestMapping("/login/addUser")
public String addUser(String userName, String HashedPassword, String email)
{
	System.out.println(userName);
	boolean success = new UserDaoImpl().addUser(userName, HashedPassword, email);
	
	if (success)
	{
		return "User Added";
	}
	else
	{
		return "Failed";
	}
}
	
}
