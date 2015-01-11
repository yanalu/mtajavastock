
package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.NotEnoughStocksToSellException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;
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
		Portfolio portfolio;
		
		try
		{
			portfolio = portfolioService.getPortfolio();
			String portfolioStr= portfolio.getHtmlString();
			resp.getWriter().println(portfolioStr);
			
		}
		
		catch (StockAlreadyExistsException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch (PortfolioFullException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch (StockNotExistException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch (BalanceException e)
		{
			resp.getWriter().println(e.getMessage());
		}
		catch (NotEnoughStocksToSellException e)
		{
			resp.getWriter().println(e.getMessage());
		}				
	}
}
