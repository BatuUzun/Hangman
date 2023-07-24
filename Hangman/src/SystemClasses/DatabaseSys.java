package SystemClasses;

import java.sql.*;

public class DatabaseSys {
	public static Connection logInToDatabase() {
		String		url = "jdbc:mysql://localhost:3306/hangman";
		String		username = "username";
		String		password = "password";
		Connection 	con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		return con;
	}
	
	public static String selectWord(Connection con) {
		String 		word = null;
		int 		randomNumber;
		String 		query;
		
		try {
			Statement statement = con.createStatement();
			ResultSet result; 
			while(word == null) {
				randomNumber = (int)(Math.random() * 127142+1);
				query = "select word from words where word_ID="+randomNumber+";";
				result = statement.executeQuery(query);
				while(result.next()) {
					word = result.getString(1);
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return word;
	}
	
	
}
