/**
 * 
 */
package rest.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

import rest.JdbcSqlServerConnection;

/**
 * @author Jonathan
 *
 */
public class LoginTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	//Not working yet
	public void TestAddUserJson()
	{
		JSONObject obj = new JSONObject();
		obj.put("userName", "TestUser2");
		obj.put("HashedPassword", "TestPass2");
		obj.put("email", "Testemail2");
		
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
		        HttpPost request = new HttpPost("http://localhost:8080/login/addUser");
		        StringEntity params =new StringEntity(obj.toJSONString());
		        request.addHeader("content-type", "application/x-www-form-urlencoded");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);
		      //  assertTrue("Pass!", response.toString() == "Failed");

		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
	}
	
	@Test
	public void TestAddUser()
	{
		String userName = "Testuser";
		String hashedPassword = "TestPass";
		String email = "TestEmail";
		
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
		    	String url = MessageFormat.format("http://localhost:8080/login/addUser?userName={0}&HashedPassword={1}&email={2}", userName,hashedPassword,email);
		        HttpPost request = new HttpPost(url);
		        HttpResponse response = httpClient.execute(request);


		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
	}

}
