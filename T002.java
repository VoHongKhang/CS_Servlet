/**
 * @(#)T002.java 01-00 2023/06/13
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
import javax.servlet.http.HttpSession;

import fjs.cs.common.Constants;
import fjs.cs.dao.T002Dao;
import fjs.cs.service.T002Service;

/**
 * T002
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */

public class T002 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Screen init
	 * 
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * @return RequestDispatcher
	 * @throws ServletException, IOException
	 * @since 1.00
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter(Constants.ACTION);

		if (action != null && action.equals(Constants.LOGOUT)) {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(Constants.T001_LOGIN_LINK);
		} else {
			RequestDispatcher myRD = null;
			String forward = new T002Service().check(req, resp);

			if (forward.equals(Constants.SUCCESS)) {
				myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
			} else if (forward.equals(Constants.ERROR)) {
				myRD = req.getRequestDispatcher(Constants.T001_LOGIN);
			}

			myRD.forward(req, resp);
		}

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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getParameter(Constants.METHOD);
		if ((Constants.DELETE).equalsIgnoreCase(method)) {

			// Get list of selected values from checkbox
			String[] selectedCustomers = req.getParameterValues(Constants.CHECKBOX);

			// Initialize customer delete function
			T002Dao customerDelete = new T002Dao();
			customerDelete.deleteById(selectedCustomers);

			// Return results
			T002Service.search(req, resp);

			// Forward page
			RequestDispatcher myRD = null;
			myRD = req.getRequestDispatcher(Constants.T002_SEARCH);
			myRD.forward(req, resp);

		} else {
			// Check search and forward
			T002Service.checkSearch(req, resp);
		}

	}

}
