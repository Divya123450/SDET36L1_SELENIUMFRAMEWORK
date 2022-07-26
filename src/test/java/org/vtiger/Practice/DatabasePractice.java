package org.vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;


public class DatabasePractice {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		//create the object for driver class which is given by db vendor
		Driver driver = new Driver();
		
		//Register the driver to jdbc 
		DriverManager.registerDriver(driver);
		
		//establish the connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name","root","root");
		
		//create statement
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("select * from sdet36;");
		
		//iterate the data
		while(result.next()) 
		{
			System.out.println(result.getString(1));
		}
	     //validate the data
		int count=0;
		while(result.next()) {
			if(result.getNString("empName").equals("Dinesh"))
			{
				System.out.println("data is present in db");
				count++;
				break;
				
			}
		}
		connection.close();
	}
}


