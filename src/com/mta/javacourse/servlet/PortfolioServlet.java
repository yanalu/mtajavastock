package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
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
		Stock[] stocks = portfolio.getStocks();
		
		//Print portfolio1

		portfolio.setTitle("Portfolio #1");
		String portfolioStr= portfolio.getHtmlString();
		resp.getWriter().println(portfolioStr);
		
		//Create copy of portfolio1 and print the copy
		
		Portfolio portfolio2= new Portfolio(portfolio);
		portfolio2.setTitle("Portfolio #2");
		String portfolioStr2= portfolio2.getHtmlString();
		resp.getWriter().println(portfolioStr2);
		
		
		//Remove first stock of portfolio1
		
		portfolio.removeFirstStock(portfolio.getStocks());
		
		//Print the portfolios after the change
		
		portfolioStr= portfolio.getHtmlString();
		resp.getWriter().println(portfolioStr);

		resp.getWriter().println(portfolioStr2);
		
		//Change the bid of portfolio2
		
		portfolio2.getStocks()[2].setBid((float) 55.55);
		
		//Print the portfolios after the change
		
		resp.getWriter().println(portfolioStr);	
		
		portfolioStr2= portfolio2.getHtmlString();
		resp.getWriter().println(portfolioStr2);
		
	}
}
