package rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rest.domain.GenericResponse;

public class UserDaoImpl implements UserDao {

	private Connection con;

	public UserDaoImpl() {
		con = new JdbcSqlServerConnection().ConnectToDb();
	}

	public GenericResponse addUser( String userName, String hashPass, String email) {

		boolean success = false;
		String message = "";
		try {
			if (getUser(userName).getUserId() != -1 )
			{
				message = "User Already Exists!";
				success = false;
				
			}
			else
			{
				String SPsql = "EXEC AddUser ?,?,?";   // for stored proc 
				PreparedStatement ps = con.prepareStatement(SPsql);
				ps.setEscapeProcessing(true);
				ps.setQueryTimeout(20);
				ps.setString(1, userName);
				ps.setString(2, hashPass);
				ps.setString(3, email);
				ps.executeUpdate();
				success = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
			message  = e.getMessage();
		}
		
		return new GenericResponse(success, message);
	}

	public User getUser(String userName) {
		User u = new User();

		String query = "select * from users where userName = '" + userName +"'";

		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				u.setUserId(rs.getInt("userId"));
				u.setUserName(rs.getString("userName"));
				u.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			
		}
		return u;
	}
	
	public GenericResponse login(String userName, String password)
	{
		boolean success = false;
		String message = "";
		int userId = -1;
		try {
			if (getUser(userName).getUserId() == -1 )
			{
				message = "User Already Exists!";
				success = false;
				
			}
			
			else
			{
				String SPsql = "EXEC LoginUser ?,?";   // for stored proc 
				PreparedStatement ps = con.prepareStatement(SPsql);
				ps.setEscapeProcessing(true);
				ps.setQueryTimeout(20);
				ps.setString(1, userName);
				ps.setString(2, password);

				ps.executeUpdate();			

				ResultSet rs = ps.executeQuery();
				if (rs.getRow() == 0 )
				{
					message = "Login Failed.";
					success = false;
				}
				else
				{
					userId = (rs.getInt("userId"));
					message = String.valueOf(userId);
				}
				success = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
			message  = e.getMessage();
		}
		
		return new GenericResponse(success, message);

	}

}
