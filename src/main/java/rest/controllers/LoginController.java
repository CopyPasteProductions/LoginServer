package rest.controllers;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.User;
import rest.UserDaoImpl;
import rest.domain.GenericResponse;
import rest.domain.LoginResponse;

@RestController
public class LoginController {

	

@RequestMapping("/login")
public GenericResponse root(String userName, String HashedPassword)
{
	return new UserDaoImpl().login(userName, HashedPassword);
}


@RequestMapping("/login/getUser")
public LoginResponse test2(String userName)
{
	User u = new UserDaoImpl().getUser(userName);
	boolean success = true;
	success =  (u.getUserName() != null);
	return new LoginResponse(success, u);
}

@RequestMapping("/login/addUser")
public GenericResponse addUser(String userName, String HashedPassword, String email)
{
	System.out.println(userName);
	return new UserDaoImpl().addUser(userName, HashedPassword, email);
	
}


/*
@RequestMapping("/login/addUser2")
public GenericResponse addUser2(RequestBody requestBody)
{
    //User user = (User) inputJsonObj.get("user");

	
	System.out.println(user.getUserName());
	return new UserDaoImpl().addUser(user.getUserName(), user.getHashedPassword(), user.getEmail());
	

}
*/

	
}
