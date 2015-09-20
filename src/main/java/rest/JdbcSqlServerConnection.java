package rest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class JdbcSqlServerConnection {
 
		public JdbcSqlServerConnection()
		{
		}
		
		public Connection ConnectToDb()
		{
        Connection conn = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://CPPTest.mssql.somee.com";
            String user = "bullsfan127_SQLLogin_1";
            String pass = "xnq2ciyn5u";

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
}


