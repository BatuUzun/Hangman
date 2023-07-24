package MainPackage;

import java.sql.*;
import java.util.ArrayList;



import GUI.ScreenFrame;
import SystemClasses.DatabaseSys;
import SystemClasses.WordSys;
public class main {
	
	public static void main(String[] args) throws SQLException {
		Connection 				con = DatabaseSys.logInToDatabase();
		ScreenFrame				screen = new ScreenFrame(con);
		screen.setVisible(true);
		
	}
	
}
