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
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
	
	/*
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
	*/
	
	@Test
	public void TestAddUser()
	{
		String userName = "Testuser";
		String hashedPassword = "TestPass";
		String email = "TestEmail";
		

		//Check if user already exists
		
		   HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
				String user = getUser(userName);
		        JSONObject jsonObject = new JSONObject(user);
		   	 	JSONObject userJson = (JSONObject) jsonObject.get("user");
		   	 	 
		   	 	if (!jsonObject.isNull("userName"))
		   	 	{
		   	 		assertTrue("User Already exists!", true);
		   	 	}
		   	 	else
		   	 	{
			    	String url = MessageFormat.format("http://localhost:8080/login/addUser?userName={0}&HashedPassword={1}&email={2}", userName,hashedPassword,email);
			        HttpPost request = new HttpPost(url);
			        HttpResponse response = httpClient.execute(request);
		   	 	}

		        // handle response here...
		    }catch (Exception ex) {
		    	fail(ex.toString());
		    }
	}
	
	@Test
	public void TestGetUser()
	{
		String userName = "Testuser";
		String user = getUser(userName);
        JSONObject jsonObject = new JSONObject(user);
        // get a String from the JSON object
        
         try {
        	 JSONObject userJson = (JSONObject) jsonObject.get("user");
        	 userName = userJson.getString("userName");
			 System.out.println(userName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
         
       assertTrue("Pass!", userName != "null");
        
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
