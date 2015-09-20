package rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

	private Connection con;

	public UserDaoImpl() {
		con = new JdbcSqlServerConnection().ConnectToDb();
	}

	public boolean addUser( String userName, String hashPass, String email) {

		boolean rv = true;
		try {
			String SPsql = "EXEC AddUser ?,?,?";   // for stored proc 
			PreparedStatement ps = con.prepareStatement(SPsql);
			ps.setEscapeProcessing(true);
			ps.setQueryTimeout(20);
			ps.setString(1, userName);
			ps.setString(2, hashPass);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rv = false;
		}
		
		return rv;
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

}
