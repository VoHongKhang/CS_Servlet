/**


 * @(#)T002Logout.java 01-00 2023/06/13
 *
 * Copyright(C) 2023 by FUJINET CO., LTD.
 *
 * Last_Update 2023/06/13.
 * Version 1.00.
 */
package fjs.cs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fjs.cs.common.Constants;
import fjs.cs.service.T002Service;
import fjs.cs.service.T003Service;

/**
 * T003
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */

public class T003 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Init screen
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher myRD = null;

		String forward = new T003Service().check(req, resp);

		if (forward.equals(Constants.SUCCESS)) {
			// Display the edit screen
			myRD = req.getRequestDispatcher(Constants.T003_EDIT);
			// Display the login screen
		} else if (forward.equals(Constants.ERROR)) {
			myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
		}

		myRD.forward(req, resp);

	}

	/**
	 * Even screen
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Event edit
		T003Service.addOrEdit(req, resp);

		// Return results
		T002Service.search(req, resp);

		// Forward page
		RequestDispatcher myRD = null;
		myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
		myRD.forward(req, resp);

	}
}
