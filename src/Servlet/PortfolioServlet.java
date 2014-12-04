package Servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PortfolioService;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.Portfolio;

public class PortfolioServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html");
		
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Stock[] stocks = portfolio.getStocks();
		
		portfolio.setTitle("FirstPortfolio");
		String portfolioStr=portfolio.getHtmlString();
		resp.getWriter().println(portfolioStr);
		
	}

}
