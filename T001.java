/**
 * @(#)T001.java 01-00 2021/06/12
 *
 * Copyright(C) 2023 by FUJINET CO., LTD.
 *
 * Last_Update 2023/06/19.
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
import fjs.cs.service.T001Service;

/**
 * T001
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */

public class T001 extends HttpServlet {

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

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher myRD = null;
		// Display login screen
		myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
		myRD.forward(req, resp);
	}

	/**
	 * Screen event
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Declare and initialize the RequestDispatcher variable
		RequestDispatcher myRD = null;

		try {
			String forward = new T001Service().check(req, resp);

			if (forward.equals(Constants.SUCCESS)) {
				// Foward to the search page if the status is success
				resp.sendRedirect(Constants.T002_SEARCH_LINK);
				return;
			} else if (forward.equals(Constants.ERROR)) {
				// Foward to the login page if the status is error
				myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Forward the request and response objects to the designated resource
		myRD.forward(req, resp);

	}
}