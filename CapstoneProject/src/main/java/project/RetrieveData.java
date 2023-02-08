package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RetrieveData extends WebServices
{
	 public String getDBData(String sqlQuery) throws SQLException , ClassNotFoundException{

		 Class.forName("com.mysql.cj.jdbc.Driver");

		 String Dataread = null;

		 // Steps to execute the sql query
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie","root","root");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
		try
		{
			if (resultSet.next())
			{
				Dataread = resultSet.getString(1);
			}
		}
		catch (SQLException ex) 
		{
			String Error = ex.getMessage();
			System.out.println("SQL Exception Error : " + Error);
		}
		return Dataread;
	}
}