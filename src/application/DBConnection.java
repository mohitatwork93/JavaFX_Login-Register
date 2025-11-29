package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{																						
		public Connection db_link;
		
		public Connection getConnection()
		{
			String database = "demo_db";
			String url = "jdbc:mysql://localhost:3306/"+database;
			String user = "root";
			String password = "Mohit12@34";
			
		
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				db_link = DriverManager.getConnection(url, user, password);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				e.getCause();
			}
			
			return db_link;
			
		}
		
}
