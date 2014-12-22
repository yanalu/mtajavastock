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
	private  enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
	private int portfolioSize;
	private String title;
	private float balance;
	private Stock[] stocks;
	private StockStatus[] stockStatus;

	/**
	 * Portfolio constructor
	 */

	public Portfolio()
	{
		balance=0;
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
	
	public void setBalance(float balance) {
		this.balance = balance;
	}

	//Getters

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
	
	public float getBalance() {
		return balance;
	}

	/**
	 *Insert new stock to the array and change the array size
	 *@param stock
	 */

	public void addStock(Stock stock)
	{
		if(portfolioSize < stocks.length)
		{
			stocks[portfolioSize] = stock;
			stockStatus[portfolioSize] = new StockStatus(stock);
			portfolioSize++;
		}
		else
			System.out.println("Can't add new stock, portfolio can have only " +MAX_PORTFOLIO_SIZE +"stocks ");
	}

	/**
	 * Removes stock from the portfolio array
	 * @param symbol
	 */

	public  boolean removeStock(String symbol)
	{
		sellStock(symbol,-1);
		for(int i=0; i<stocks.length;i++)
			if(symbol.equals(stocks[i].getSymbol()))
			{
				stocks[i] = stocks[portfolioSize-1];
				stocks[portfolioSize-1] =null;
				stockStatus[i] = stockStatus[portfolioSize-1];
				stockStatus[portfolioSize-1] =null;
				portfolioSize--;
				return true;
			}
		return false;
	}
	
	/**
	 * Update the balance after sell/buy
	 * @param amount
	 */

	public void updateBalance(float amount)
	{
		balance += amount;
	}
	
	/**
	 * Sell stocks and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public boolean sellStock(String symbol, int quantity )
	{

		for(int i=0; i<stocks.length; i++)
			if(symbol.equals(stocks[i].getSymbol()))
			{
				if(quantity == -1) {
					float amount1=stockStatus[i].getStockQuantity()*stockStatus[i].getCurrentBid();
					updateBalance(amount1);
					stockStatus[i].setStockQuantity(0);
				}
				else if(stockStatus[i].getStockQuantity()-quantity < 0){
						System.out.println("Not enough stocks to sell");
				}
				else if (stockStatus[i].getStockQuantity()-quantity >= 0){
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()-quantity);
					float amount = quantity*stockStatus[i].getCurrentBid();
					updateBalance(amount);
				}
				return true;
			}
		return false;
	}
	
	/**
	 * Buy stocks and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public boolean buyStock(String symbol, int quantity )
	{

		for(int i=0; i<stocks.length;i++)
			if(symbol.equals(stocks[i].getSymbol()))
			{
				if( quantity == -1) {
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+ (int)(balance/stockStatus[i].getCurrentAsk()));
					float spent = ((int)(balance/stockStatus[i].getCurrentAsk()) *stockStatus[i].getCurrentAsk())/(-1); //how much bought
					updateBalance(spent);

				}
				else{
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+quantity);
					float spent1=(quantity*stockStatus[i].getCurrentAsk())/(-1);
					updateBalance(spent1);
				}
				return true;
			}
		return false;
	}

	/**
	 * Method that returns HTML description in bold font with break between lines
	 * @return
	 */

	public String getHtmlString() 
	{

		String stockStr= "<h1><center>" + getTitle() + "</center></h1>" + "<br/>";
		
		stockStr +="<b> Total Portfolio Value: </b>" + getTotalValue() +"$ <b>Total Stocks Value: </b>"+ getStocksValue() + "$ <b>Balance: </b>"+ getBalance() +"$ <br/><br/>";

		for(int i=0; i<portfolioSize; i++)
			stockStr += stocks[i].getHtmlDescription() + "<br/>";


		return stockStr;
	}
	
	/**
	 * Calculate value of stocks
	 * @return
	 */

	public float getStocksValue()
	{
		float sum =0;
		for(int i =0; i<portfolioSize; i++)
			sum+=stockStatus[i].getStockQuantity() * stockStatus[i].getCurrentBid();

		return  sum;
	}
	
	/**
	 * Return total value (balance+stocks value)
	 * @return
	 */

	public float getTotalValue()
	{
		return getBalance() + getStocksValue();
	}

	/**
	 *Inner class in Portfolio represent StockStatus
	 */

	public class StockStatus
	{
		private String symbol;
		private float currentBid,currentAsk;
		private Date date;
		private  ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;

		/**
		 * StockStatus constructor
		 */
		
		public StockStatus(){
			symbol= "None";
			currentAsk=0;
			currentBid=0;
			date=new Date();
			recommendation= ALGO_RECOMMENDATION.DO_NOTHING;
			stockQuantity=0;

		};

		/**
		 *Copy constructor StockStatus values
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
		
		/**
		 * Copy constructor of StockStatus with stock values  
		 * @param stock
		 */

		public StockStatus(Stock stock)
		{
			this();
			setSymbol(stock.getSymbol());
			setCurrentBid(stock.getBid());
			setCurrentAsk(stock.getAsk());
			setDate(stock.getDate());
			setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
			setStockQuantity(0);
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

		public ALGO_RECOMMENDATION getRecommendation() {
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

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}
}
