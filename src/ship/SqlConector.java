package ship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConector {
	private static String ship = "ship12";
	
	
	
	
	public String getSave(int x, int y//,ship
			){
		/*
		 this.ship = ship;
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			return "driver Instantiation eror";
		} catch (IllegalAccessException e) {
			return "driver IllegalAccess eror";
		} catch (ClassNotFoundException e) {
			return "driver ClassNotFound eror";
		}
		
		
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/play?user=root&password=");
			Statement st=conn.createStatement();
			st.execute("CREATE TABLE "+ship+"(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(10), chipPositionX INT, chipPositionY INT);");
			st.execute(" INSERT INTO "+ship+"(name, chipPositionX, chipPositionY) VALUES('titanik',"+x+","+y+");");
			st.close();
			conn.close();
		} catch (SQLException e) {
			return "SQL exeption";
		}
		
		return "Game save";	
		
	}
	public static int[] getLoadGame(){
		int[] position = new int[2];
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/play?user=root&password=");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM "+ ship +" WHERE id=1;");//SELECT * FROM users WHERE id=1;
			rs.next();
			position[0] =Integer.parseInt(rs.getString("chipPositionX"));
			position[1] =Integer.parseInt(rs.getString("chipPositionY"));
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			return null;
		}
		return position;
	}

}
