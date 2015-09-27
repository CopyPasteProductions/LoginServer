package rest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * 
 * @author www.codejava.net
 *
 */

public class JdbcSqlServerConnection {

	String dbURL = "";
	String user = "";
	String pass = "";

	public JdbcSqlServerConnection() {

	}

	public Connection ConnectToDb() {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");

		JdbcSqlServerConnection obj = (JdbcSqlServerConnection) context.getBean("JdbcSqlServerConnection");
		this.dbURL = obj.dbURL;
		this.pass = obj.pass;
		this.user = obj.user;

		Connection conn = null;

		try {

			conn = DriverManager.getConnection(dbURL, user, pass);

			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return conn;
	}

	public String getDbURL() {
		return dbURL;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
