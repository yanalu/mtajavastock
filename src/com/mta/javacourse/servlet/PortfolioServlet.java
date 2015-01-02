
package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.StockStatus;
import com.mta.javacourse.service.PortfolioService;


/**
 *Class that print portfolio data
 *@author Yana Lukashik
 * December 2014
 */

public class PortfolioServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");

		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		StockStatus[] stocks = portfolio.getStockStatus();
		
		//Print portfolio

		String portfolioStr= portfolio.getHtmlString();
		resp.getWriter().println(portfolioStr);
				
	}
}
