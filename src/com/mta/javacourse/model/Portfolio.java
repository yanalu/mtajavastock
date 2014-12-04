package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.Stock;
public class Portfolio {

	 private final static int MAX_PORTFOLIO_SIZE=5;
	 int portfolioSize=0;
	 String title;
	 
	 private Stock[] stocks;
	 private StockStatus[] stockStatus;
	 

	public Portfolio()
	{
		 stockStatus=new StockStatus[MAX_PORTFOLIO_SIZE];
		 stocks=new Stock[MAX_PORTFOLIO_SIZE];
	}
	 
	public void setStocks(Stock[] stocks1)
	{
		stocks=stocks1;
	}
	public void setTitle(String title1)
	{
		title=title1;
	}
	public void setStockStatus(StockStatus[] stockStatus1)
	{
		stockStatus=stockStatus1;
	}

	 public Stock[] getStocks()
	 {
		 return stocks;
	 }
	 public String getTitle()
	 {
		 return title;
	 }
	 
	//methods/
	 public void addStock(Stock stock)
	 {
			 stocks[portfolioSize] = stock;
			 portfolioSize++;
		 
	 }

	 public String getHtmlString() 
	 {
		
		String stockStr;
		
		String s1String=stocks[0].getHtmlDescription();
		String s2String=stocks[1].getHtmlDescription();
		String s3String=stocks[2].getHtmlDescription();
		
		stockStr="<h1>"+title+"</h1>"+"<br>"+s1String+"<br>"+s2String+"<br>"+s3String;
		return stockStr;
	 }

	 public class StockStatus {
			private final static int DO_NOTHING = 0;
			private final static int BUY = 1;
			private final static int SELL = 2;	
					
			String symbol;
			float currentBid,currentAsk;
			Date date;
			int recommendation;
			int stockQuantity;	
		}
}
