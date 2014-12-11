package com.mta.javacourse.model;

import java.util.Date;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 *Class represent Portfolio
 *@author Yana Lukashik
 *December 2014
 */

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE=5;
	int portfolioSize;
	String title;

	private Stock[] stocks;
	private StockStatus[] stockStatus;

	/**
	 * Portfolio constructor
	 */

	public Portfolio()
	{
		portfolioSize=0;
		this.setTitle("portfolio");
		stockStatus=new StockStatus[MAX_PORTFOLIO_SIZE];
		stocks=new Stock[MAX_PORTFOLIO_SIZE];
	}

	/**
	 *copy constructor Portfolio
	 *@param portfolio
	 */

	public Portfolio(Portfolio portfolio)
	{
		this();
		setTitle(portfolio.getTitle());
		setPortfolioSize(portfolio.getPortfolioSize());

		for(int i=0; i<portfolioSize; i++)
			stocks[i]= new Stock(portfolio.getStocks()[i]);

		for(int i=0; i<portfolioSize; i++)
			stockStatus[i]= new StockStatus(portfolio.getStockStatus()[i]);
	}
	
	//Setters

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
	
	public void setPortfolioSize(int portfolioSize)
	{
		this.portfolioSize=portfolioSize;
	}
	
	//Setters
	
	public Stock[] getStocks()
	{
		return stocks;
	}

	public StockStatus[] getStockStatus()
	{
		return stockStatus;
	}

	public String getTitle()
	{
		return title;
	}

	public int getPortfolioSize()
	{
		return portfolioSize;
	}

	/**
	 *Insert new stock to the array and change the array size
	 *@param stock
	 */

	public void addStock(Stock stock)
	{
		if(portfolioSize < MAX_PORTFOLIO_SIZE)
		{
			stocks[portfolioSize] = stock;
			stockStatus[portfolioSize] = new StockStatus();
			portfolioSize++;
		}
	}

	/**
	 * Remove the first stock at the portfolio array
	 * @param portfolio
	 */

	public void removeFirstStock(Stock[] stocks)
	{
		this.portfolioSize--;
		for(int i = 0; i < this.portfolioSize; i++)
			this.stocks[i] = this.stocks[i+1];
	}

	/**
	 *Method that returns HTML description in bold font with break between lines
	 */

	public String getHtmlString() 
	{

		String stockStr= "<h1>" + getTitle() + "</h1>" + "<br/>";

		for(int i=0; i<portfolioSize; i++)
			stockStr += stocks[i].getHtmlDescription() + "<br/>";

		return stockStr;
	}

	/**
	 *Inner class in Portfolio represent StockStatus
	 */

	public class StockStatus
	{
		private final static int DO_NOTHING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;	

		private String symbol;
		private float currentBid,currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		
		/**
		 * StockStatus constructor
		 */

		public StockStatus(){};

		/**
		 *copy constructor StockStatus
		 *@param StockStatus
		 */

		public StockStatus(StockStatus stockstatus)
		{
			this();
			setSymbol(stockstatus.getSymbol());
			setCurrentBid(stockstatus.getCurrentBid());
			setCurrentAsk(stockstatus.getCurrentBid());
			setDate(stockstatus.getDate());
			setRecommendation(stockstatus.getRecommendation());
			setStockQuantity(stockstatus.getStockQuantity());
		}

		//Getters

		public String getSymbol() {
			return symbol;
		}
		
		public float getCurrentBid() {
			return currentBid;
		}
		
		public float getCurrentAsk() {
			return currentAsk;
		}
		
		public Date getDate() {
			return date;
		}
		
		public int getRecommendation() {
			return recommendation;
		}
		
		public int getStockQuantity() {
			return stockQuantity;
		}
		
		//Setters
		
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}

		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}
