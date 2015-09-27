/**
 * 
 */
package rest.test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.MessageFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rest.JdbcSqlServerConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Jonathan
 *
 */
public class LoginTest {
	
	String userName = "Testuser2";
	String hashedPassword = "TestPass2";
	String email = "TestEmail2";
	

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
	
/*	
	//Not working yet
	@Test
	public void TestAddUserJson()
	{
		JSONObject obj = new JSONObject();
		obj.put("userName", "TestUser2");
		obj.put("HashedPassword", "TestPass2");
		obj.put("email", "Testemail2");
		
		
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
		        HttpPost request = new HttpPost("http://localhost:8080/login/addUser2");
		        StringEntity params =new StringEntity(obj.toString());
		        request.addHeader("content-type", "application/json");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);
		      //  assertTrue("Pass!", response.toString() == "Failed");

		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
	} */
	
	
	@Test
	public void TestAddUser()
	{
		//Check if user already exists
		
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
		   boolean userFound =false;
		    try {
				String user = getUser(userName);
		        JSONObject jsonObject = new JSONObject(user);
		        userFound = jsonObject.getBoolean("success");
		   	 	 
		   	 	if (userFound == true)
		   	 	{
		   	 		assertTrue("User Already exists!", false);
		   	 	}
		   	 	else
		   	 	{
			    	String url = MessageFormat.format("http://localhost:8080/login/addUser?userName={0}&HashedPassword={1}&email={2}", userName,hashedPassword,email);
			        HttpPost request = new HttpPost(url);
			        HttpResponse response = httpClient.execute(request);
			        String rv = EntityUtils.toString(response.getEntity());
			        userFound = jsonObject.getBoolean("success");
			        
			        assertTrue("User Not Added!", userFound != true);
		   	 	}

		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
	}
	
	@Test
	public void TestGetUser()
	{
		String user = getUser(userName);
        JSONObject jsonObject = new JSONObject(user);
        boolean success =false;
        // get a String from the JSON object
        
         try {
        	  success = jsonObject.getBoolean("success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
         
       assertTrue("Failed!", success == true);
        
	}
	
	@Test
	public void TestLogin()
	{
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
			    	String url = MessageFormat.format("http://localhost:8080/login/Loginr?userName={0}&HashedPassword={1}", userName,hashedPassword);
			        HttpPost request = new HttpPost(url);
			        HttpResponse response = httpClient.execute(request);
		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
        
	}
	
	private String getUser(String userName)
	{
		String rv = "";
		   HttpClient httpClient = HttpClientBuilder.create().build();

		    try {
		    	String url = MessageFormat.format("http://localhost:8080/login/getUser?userName={0}", userName);
		        HttpPost request = new HttpPost(url);
		        HttpResponse response = httpClient.execute(request);
		         rv = EntityUtils.toString(response.getEntity());
		        System.out.println(rv);
		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
		    
		    return rv;
	}

}
