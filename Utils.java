/**
 * @(#)Constants.java 01-00 2023/06/19
 *
 * 
 *  Copyright(C) 2023 by FUJINET CO., LTD.
 *  
 * Last_Update 2023/06/19
 * Version 1.00.
 */
package fjs.cs.common;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fjs.cs.model.T002Customer;


/**
 * Utils
 * 
 * @version 1.00
 * @since 1.00
 * @author Khang-VoH-TTV
 * 
 */
public class Utils {
	/**
	 * Paging handler function
	 * 
	 * @param List<T002Customer> customerList
	 * @param HttpServletRequest  req
	 * @param HttpServletResponse resp
	 * 
	 * @since 1.00
	 */
	public static void Paging(List<T002Customer> customerList, HttpServletRequest req, HttpServletResponse resp) {

		int pageSize = 15;

		// Current page (default is the first page)
		int currentPage = 1;
		String currentPageParam = req.getParameter(Constants.CURRENTPAGE);
		if (currentPageParam != null && currentPageParam != "") {
			currentPage = Integer.parseInt(currentPageParam);
		}

		// Number of customers / pagessize
		int totalPages = (int) Math.ceil((double) customerList.size() / pageSize);

		// Calculate the start and end positions of
		// data displayed on current page
		int startIdx = (currentPage - 1) * pageSize;
		int endIdx = Math.min(startIdx + pageSize, customerList.size());

		// Get the list of data displayed on the current page
		List<T002Customer> currentPageData = customerList.subList(startIdx, endIdx);

		// Count the number of customers
		int count = 0;
		for (T002Customer item : currentPageData) {
			count++;
		}
		
		
		// Send pagination information and data lists to JSP
		req.setAttribute(Constants.TOTALPAGES, totalPages);
		req.setAttribute(Constants.CURRENTPAGE, currentPage);
		req.setAttribute(Constants.DATALIST, currentPageData);
		req.setAttribute(Constants.COUNT, count);
	}

	public static boolean validateDateInput(String input) {
		

		// Allow empty date
		if (input.isEmpty()) {
			return true;
		}

		// Check "yyyy/mm/dd" format with regex
		String regex = Constants.REGEX;

		if (!input.matches(regex)) {
			return false;
		}

		// Split day, month, year from input string
		String[] parts = input.split("/");
		int year = Integer.parseInt(parts[0], 10);
		int month = Integer.parseInt(parts[1], 10);
		int day = Integer.parseInt(parts[2], 10);

		// Check valid year (from 1900 to 9999)
		if (year < 1900 || year > 9999) {
			return false;
		}

		// Check valid month (from 1 to 12)
		if (month < 1 || month > 12) {
			return false;
		}

		// Check valid date based on month and year
		int maxDay;
		if (month == 2) {
			maxDay = isLeapYear(year) ? 29 : 28;
		} else if (month == 4 || month == 6 || month == 9 || month == 12) {

			maxDay = 30;
		} else {
			maxDay = 31;
		}

		if (day < 1 || day > maxDay) {
			return false;
		}

		return true;
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
}
