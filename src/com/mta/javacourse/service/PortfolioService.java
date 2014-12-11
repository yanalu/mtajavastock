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
	 * create new portfolio and add new stocks
	 * @return
	 */

	public Portfolio getPortfolio()
	{
		Portfolio myPortfolio= new Portfolio();
	
		Calendar c = Calendar.getInstance();
		c.set(2014, 10, 15, 0, 0, 0);
		Date newDate = c.getTime();

		Stock s1 = new Stock("PIN", 12.4f, 13.1f, newDate);
		Stock s2 = new Stock("AAL", 5.5f, 5.78f, newDate);
		Stock s3 = new Stock("CAAS", 31.5f, 31.2f, newDate);

		myPortfolio.setTitle("myPortfolio");
		myPortfolio.addStock(s1);
		myPortfolio.addStock(s2);
		myPortfolio.addStock(s3);
		
		return myPortfolio;

	}
}
