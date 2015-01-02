package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

/**
 *Class include data of stocks using Portfolio class
 *@author Yana Lukashik
 *December 2014
 */

public class PortfolioService {
	

	/**
	 * Create new portfolio and add new stocks
	 * @return
	 */

	public Portfolio getPortfolio()
	{
		Portfolio myPortfolio= new Portfolio();
		myPortfolio.setTitle("<b>Exercise 08 - Portfolio</b>");
		myPortfolio.setBalance(10000);
		
	
		Calendar c = Calendar.getInstance();
		c.set(2014, 12, 15, 0, 0, 0);
		Date newDate = c.getTime();

		Stock s1 = new Stock("PIH", 10f, 8.5f, newDate);
		Stock s2 = new Stock("AAL", 30f, 25.5f, newDate);
		Stock s3 = new Stock("CAAS", 20f, 15.5f, newDate);

		myPortfolio.addStock(s1);
		myPortfolio.addStock(s2);
		myPortfolio.addStock(s3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		
		myPortfolio.sellStock("AAL", -1);	
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;

	}
}

