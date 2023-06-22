/**
 * @(#)T001Dao.java 01-00 2023/06/12
 *
 * Copyright(C) 2023 by FUJINET CO., LTD.
 *
 * Last_Update 2023/06/21
 * Version 1.00.
 */
package fjs.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import fjs.cs.common.Constants;
import fjs.cs.common.DBConnection;
import fjs.cs.model.T001User;

/**
 * T001Dao
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */

public class T001Dao {

	// Declare connection variables
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	/**
	 * Check login
	 * 
	 * @param String user
	 * @param String pass
	 * @return T001User a
	 * @since 1.00
	 */

	public T001User checkLogin(String userid, String password) {
		try {
			// Get a database connection
			conn = new DBConnection().getConnection();
			
			// Prepare the SQL statement with the query
			ps = conn.prepareStatement(Constants.T001USER_QUERY);
			ps.setString(1, userid);
			ps.setString(2, password);
			
			// Execute the query and get the result set
			rs = ps.executeQuery();
			
			// Loop through the result set
			while (rs.next()) {
				
				// Create a new T001User object and set its properties from the result set
				T001User user = new T001User();
				user.setId(rs.getInt(Constants.T001DAO_PSN_CD));
				user.setUserId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setUpdate_psn_cd(rs.getInt(Constants.T001DAO_UPDATE_PSN_CD));
				user.setInsert_psn_cd(rs.getInt(Constants.T001DAO_INSERT_PSN_CD));
				user.setUserName(rs.getString(Constants.T001DAO_USERNAME));
				
				// Return the T001User object
				return user;
				
			}
			

		} catch (Exception e) {
			// Print the exception message if an error occurs
			System.out.println(e.getMessage());
		}
		// Return null if no user is found or an error occurs
		return null;
	}
	
	public T001User getUserInfo(String userid) {
		try {
			// Get a database connection
			conn = new DBConnection().getConnection();
			
			// Prepare the SQL statement with the query
			ps = conn.prepareStatement(Constants.T001USERINFO_QUERY);
			ps.setString(1, userid);
			
			
			// Execute the query and get the result set
			rs = ps.executeQuery();
			
			// Loop through the result set
			while (rs.next()) {
				
				// Create a new T001User object and set its properties from the result set
				T001User user = new T001User();
				user.setId(rs.getInt(Constants.T001DAO_PSN_CD));
				user.setUserId(rs.getString(Constants.T001DAO_USERID));
				user.setUpdate_psn_cd(rs.getInt(Constants.T001DAO_UPDATE_PSN_CD));
				user.setInsert_psn_cd(rs.getInt(Constants.T001DAO_INSERT_PSN_CD));
				user.setPassword(rs.getString(Constants.T001DAO_USERID));
				user.setUserName(rs.getString(Constants.T001DAO_USERNAME));
				
				// Return the T001User object
				return user;
			}

		} catch (Exception e) {
			// Print the exception message if an error occurs
			System.out.println(e.getMessage());
		}
		// Return null if no user is found or an error occurs
		return null;
	}
	
}
