/**
 * @(#)T002Dao.java 01-00 2023/06/13
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
import java.util.ArrayList;
import java.util.List;

import fjs.cs.common.Constants;
import fjs.cs.common.DBConnection;
import fjs.cs.model.T002Customer;

/**
 * T002Dao
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */

public class T002Dao {

	// Declare connection variables
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;

	/**
	 * Get list customer
	 * 
	 * @param String user
	 * @param String pass
	 * @return T001User a
	 * @since 1.00
	 */

	public List<T002Customer> getList() {
		List<T002Customer> customerList = new ArrayList<>();

		try {
			// Get a database connection
			conn = new DBConnection().getConnection();

			// Prepare the SQL statement with the query
			ps = conn.prepareStatement(Constants.T002CUSTOMER_QUERY);

			// Execute the query and get the result set
			rs = ps.executeQuery();

			// Loop through the result set
			while (rs.next()) {

				// Create a new T001User object and set its properties from the result set
				T002Customer customer = new T002Customer();
				customer.setCustomerId(rs.getInt(Constants.T002DAO_CUSTOMER_ID));
				customer.setCustomerName(rs.getString(Constants.T002DAO_CUSTOMER_NAME));
				customer.setSex(rs.getString(Constants.T002DAO_SEX));
				customer.setEmail(rs.getString(Constants.T002DAO_EMAIL));
				customer.setBirthday(rs.getString(Constants.T002DAO_BIRTHDAY));
				customer.setAddress(rs.getString(Constants.T002DAO_ADDRESS));

				customerList.add(customer);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerList;
	}

	/**
	 * Customer search function
	 * 
	 * @param String customerName
	 * @param String sex
	 * @param String birthdayFrom
	 * @param String birthdayTo
	 * @since 1.00
	 * @return customer list
	 */

	public List<T002Customer> getListSearch(String customerName, String sex, String birthdayFrom, String birthdayTo) {
		StringBuilder query = new StringBuilder();
		query.append(Constants.T002SEACRH_QUERY1);
		if (customerName.trim() != "") {
			query.append(Constants.T002SEACRH_QUERY2 + customerName + "%' ");
		}
		if (!sex.equals("-1")) {
			query.append(Constants.T002SEACRH_QUERY3 + sex + "' ");
		}
		if (birthdayFrom.trim() != "") {
			query.append(Constants.T002SEACRH_QUERY4 + birthdayFrom + "') ");
		}
		if (birthdayTo.trim() != "") {
			query.append(Constants.T002SEACRH_QUERY5 + birthdayTo + "') ");
		}
		query.append(Constants.T002SEACRH_QUERY6);
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			List<T002Customer> list = new ArrayList<T002Customer>();
			while (rs.next()) {
				T002Customer customer = new T002Customer();
				customer.setCustomerId(rs.getInt(Constants.T002DAO_CUSTOMER_ID));
				customer.setCustomerName(rs.getString(Constants.T002DAO_CUSTOMER_NAME));
				customer.setSex(rs.getString(Constants.T002DAO_SEX));
				customer.setBirthday(rs.getString(Constants.T002DAO_BIRTHDAY));
				customer.setAddress(rs.getString(Constants.T002DAO_ADDRESS));
				list.add(customer);
			}
			return list;
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return null;
	}
	
	


	/**
	 * Customer delete function
	 * 
	 * @param String selectedCustomers
	 * @since 1.00
	 * @return customer list
	 */
	public void deleteById(String[] selectedCustomers) {

		try {
			// Get a database connection
			conn = new DBConnection().getConnection();

			String sql = Constants.T002DELETE;

			// Construct a CUSTOMER_ID list string to use in an SQL statement
			for (int i = 0; i < selectedCustomers.length; i++) {
				if (i > 0) {
					sql += ",";
				}
				sql += "?";
			}
			sql += ")";

			ps = conn.prepareStatement(sql);

			// Assign the value CUSTOMER_ID to the SQL statement
			for (int i = 0; i < selectedCustomers.length; i++) {
				ps.setInt(i + 1, Integer.parseInt(selectedCustomers[i]));
			}

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Customer search function in the system based on customer ID
	 * 
	 * @param Integer customerId
	 * @since 1.00
	 * @return customer information
	 */
	public T002Customer getCustomerByID(Integer customerid) {
		try {

			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(Constants.T002GETCUSTOMERBYID);
			ps.setInt(1, customerid);
			rs = ps.executeQuery();
			while (rs.next()) {
				T002Customer customer = new T002Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(Constants.T002DAO_CUSTOMER_NAME));
				customer.setSex(rs.getString(Constants.T002DAO_SEX));
				customer.setBirthday(rs.getString(Constants.T002DAO_BIRTHDAY));
				customer.setEmail(rs.getString(Constants.T002DAO_EMAIL));
				customer.setAddress(rs.getString(Constants.T002DAO_ADDRESS));
				return customer;
			}
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Customer edit function in the system based on customer ID
	 * 
	 * @param T002Customer customer
	 * @param int UPDATE_PSN_CD
	 * @since 1.00
	 * 
	 */
	public void editCustomer(T002Customer customer, int UPDATE_PSN_CD) {		
		try {
			conn = new DBConnection().getConnection();
			
			ps = conn.prepareStatement(Constants.T002EDIT_QUERY);
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getSex());
			ps.setString(3, customer.getBirthday());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setInt(6, UPDATE_PSN_CD);
			ps.setInt(7, customer.getCustomerId());	
			ps.executeUpdate();	
			ps.close();

					
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	
	/**
	 * Customer add function 
	 * 
	 * @param T002Customer customer
	 * @param int INSERT_PSN_CD
	 * @param int UPDATE_PSN_CD
	 * @since 1.00
	 * 
	 */
	public void addCustomer(T002Customer customer,int INSERT_PSN_CD, int UPDATE_PSN_CD) {		
		try {
			conn = new DBConnection().getConnection();
 
			ps = conn.prepareStatement(Constants.T002INSERT_QUERY);
			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getSex());
			ps.setString(3, customer.getBirthday());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setInt(6, INSERT_PSN_CD);
			ps.setInt(7, UPDATE_PSN_CD);
			ps.executeUpdate();
			ps.close();					
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
	}


}
