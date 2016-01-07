package com.jent.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBaseHelper {
	private static Logger log = LoggerFactory.getLogger(DataBaseHelper.class);
	
	public static Connection getDBConnection(){
		Connection conn = null;
		String url = "jdbc:postgresql://127.0.0.1:5432/test";
		String uname = "postgres";
		String password = "root";
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, uname, password); 
		} catch (Exception e) {
			log.error("get connection error : " + e.getMessage());
		}
		
		return conn;
	}
	
	public static void cleanConnection(Connection conn){
		log.info("close Connection ");
		try {
			if(conn != null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			log.error("close connection error : " + e.getMessage());
		}
	}
	
	public static void cleanResultSet(ResultSet rs){
		log.info("close ResultSet.");
		try {
			if(!rs.isClosed()){
				rs.close();
			}
		} catch (SQLException e) {
			log.error("close ResultSet error : " + e.getMessage());
		}
	}
}
