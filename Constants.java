/**
 * @(#)Constants.java 01-00 2023/06/12
 *
 * 
 *  Copyright(C) 2023 by FUJINET CO., LTD.
 *  
 * Last_Update 2023/06/13
 * Version 1.00.
 */
package fjs.cs.common;

/**
 * Constants
 * 
 * @version 1.00
 * @since  1.00
 * @author Khang-VoH-TTV
 * 
 */
public class Constants {

	/* List Screen */
	// T001(Login)
	public static final String T001_LOGIN = "/WEB-INF/jsp/T001.jsp";
	public static final String T001_LOGIN_LINK = "/CustomerSystem/T001";
	// T002(Search)
	public static final String T002_SEARCH = "/WEB-INF/jsp/T002.jsp";
	public static final String T002_SEARCH_LINK = "/CustomerSystem/T002";
	// T002(Edit)
	public static final String T003_EDIT = "/WEB-INF/jsp/T003.jsp";
	public static final String T003_EDIT_LINK = "/CustomerSystem/T003";
	
	
	
	/* List Connection Variables */
	public static final String JDBC = "jdbc:sqlserver://";
	public static final String DATABASE = ";databaseName=";
	public static final String SQLSERVERDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SERVERNAME = "localhost";
	public static final String DBNAME = "CustomerSystem";
	public static final String INSTANCE = "";
	public static final String USERNAME = "sa";
	public static final String PASSWORD = "khang2002#";
	public static final String CONNECT_SUCCESS = "Connect successfully!";
	public static final String CONNECT_FAIL = "Connect fail!";
	
	
	/* List Query */
	public static final String T001USER_QUERY = "select * from MSTUSER where DELETE_YMD is null and USERID = ? and PASSWORD = ?";
	public static final String T002CUSTOMER_QUERY = "Select CUSTOMER_ID,CUSTOMER_NAME,SEX,BIRTHDAY,ADDRESS from MSTCUSTOMER where DELETE_YMD is null order by CUSTOMER_ID";
	public static final String T002SEACRH_QUERY1 = "Select CUSTOMER_ID,CUSTOMER_NAME,SEX,BIRTHDAY,ADDRESS from MSTCUSTOMER  where DELETE_YMD is null ";
	public static final String T002SEACRH_QUERY2 = "and CUSTOMER_NAME LIKE '%";
	public static final String T002SEACRH_QUERY3 = "and SEX = '";
	public static final String T002SEACRH_QUERY4 = "and CONVERT(datetime2,BIRTHDAY) >= CONVERT(datetime2,'";
	public static final String T002SEACRH_QUERY5 = "and CONVERT(datetime2,BIRTHDAY) <= CONVERT(datetime2,'";
	public static final String T002SEACRH_QUERY6 = "order by CUSTOMER_ID";
	public static final String T002DELETE = "UPDATE MSTCUSTOMER SET DELETE_YMD = GETDATE() WHERE CUSTOMER_ID IN (";
	public static final String T002GETCUSTOMERBYID = "Select CUSTOMER_ID, CUSTOMER_NAME, SEX, BIRTHDAY, EMAIL, ADDRESS from "+ "MSTCUSTOMER where DELETE_YMD is null and  CUSTOMER_ID =?";
	public static final String T002EDIT_QUERY = "UPDATE MSTCUSTOMER SET CUSTOMER_NAME = ?, SEX =?, BIRTHDAY =?, EMAIL = ?, ADDRESS = ?, DELETE_YMD = NULL,UPDATE_YMD = GETDATE(), UPDATE_PSN_CD = ? WHERE CUSTOMER_ID = ?";
	public static final String T002INSERT_QUERY = "INSERT into MSTCUSTOMER  VALUES (?,?,?,?,?,NULL,GETDATE(),?,NULL,?)";
	public static final String T001USERINFO_QUERY = "SELECT * FROM MSTUSER WHERE USERID = ? ";
	
    /* List Parameter*/
	/* T001 */
	public static final String USERID = "userID";
	public static final String PASS = "password";
	/* T002 */
	public static final String CUSTOMER_NAME = "customer_Name";
	public static final String SEX = "sex";
	public static final String BIRTHDAYSTART = "birthDayStart";
	public static final String BIRTHDAYEND = "birthDayEnd";
	public static final String ERRORMSG = "errorMsg";
	public static final String INVALID_BIRTHDAY_FROM = "Invalid Birthday (From).";
	public static final String INVALID_BIRTHDAY_TO = "Invalid Birthday (To).";
	public static final String OUT_OF_RANGE = "There is an error in the range input of Birthday.";
	public static final String YYYY_MM_DD = "yyyy/MM/dd";
	public static final String METHOD = "_method";
	public static final String DELETE = "delete";
	public static final String CHECKBOX = "selectedCustomers";
	public static final String ACTION = "action";
	public static final String LOGOUT = "logout";
	
	
	/* List Message*/
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String ERRORMESSAGE = "errorMessage";
	public static final String USERIDLENGTH = "UserId must be less than 8 characters";
	public static final String USERIDNULL = "ユーザーIDを入力してください";
	public static final String PASSLENGTH = "Password must be less than 8 characters";
	public static final String PASSNULL = "パスワードを入力してください";
	public static final String PASSANDUSERIDNOTVALID = "ユーザーIDまたはパスワードが不正です";
	
	
	/* List T001Dao Param*/
	public static final String T001DAO_PSN_CD = "PSN_CD";
	public static final String T001DAO_USERID = "USERID";
	public static final String T001DAO_UPDATE_PSN_CD = "UPDATE_PSN_CD";
	public static final String T001DAO_INSERT_PSN_CD = "INSERT_PSN_CD";
	public static final String T001DAO_PASSWORD = "PASSWORD";
	public static final String T001DAO_USERNAME = "USERNAME";
	
	/* List T002Dao Param*/
	public static final String T002DAO_CUSTOMER_ID = "CUSTOMER_ID";
	public static final String T002DAO_CUSTOMER_NAME = "CUSTOMER_NAME";
	public static final String T002DAO_SEX = "SEX";
	public static final String T002DAO_BIRTHDAY = "BIRTHDAY";
	public static final String T002DAO_ADDRESS = "ADDRESS";
	public static final String T002DAO_EMAIL = "EMAIL";
	
	
	/* Session of user */
	public static final String USER = "user";
	
	/* Session of customer */
	public static final String CUSTOMER = "customer";
	
	
	/* T003Service Param*/
	public static final String ID = "id";
	public static final String CUSTOMERID = "cusId";
	public static final String CUSTOMERNAME = "cusName";
	public static final String CUSTOMERSEX = "cusSex";
	public static final String CUSBIRTHDAY = "cusBirthday";
	public static final String CUSEMAIL = "cusEmail";
	public static final String CUSADDRESS = "cusAddress";
	
	
	/* Utils Param */
	public static final String CURRENTPAGE = "currentPage";
	public static final String TOTALPAGES = "totalPages";
	public static final String DATALIST = "dataList";
	public static final String COUNT = "count";
	public static final String REGEX = "^\\d{4}/\\d{2}/\\d{2}$";
	
	
	
	

}
