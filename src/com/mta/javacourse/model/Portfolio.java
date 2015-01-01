package com.mta.javacourse.model;

/**
 *Class represent Portfolio
 *@author Yana Lukashik
 *December 2014
 */

public class Portfolio {

	private static final int MAX_PORTFOLIO_SIZE=5;
	public  enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
	private int portfolioSize;
	private String title;
	private float balance;
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
	}

	/**
	 *Copy constructor Portfolio
	 *@param portfolio
	 */

	public Portfolio(Portfolio portfolio)
	{
		this();
		setTitle(portfolio.getTitle());
		setPortfolioSize(portfolio.getPortfolioSize());

		for(int i=0; i<portfolioSize; i++)
			stockStatus[i]= new StockStatus(portfolio.getStockStatus()[i]);
	}

	/**
	 *Insert new stock to the array and change the array size
	 *@param s1
	 */

	public void addStock(Stock s1)
	{
		for(int i=0; i < portfolioSize; i++)
		{
			if(this.stockStatus[i].getSymbol().equals(s1.getSymbol()))
			{
				System.out.println("Stock already exists");
			}
		}
		if(portfolioSize < stockStatus.length)
		{
			stockStatus[portfolioSize] = new StockStatus(s1);
			portfolioSize++;
		}

		else
			System.out.println("Can't add new stock, portfolio can have only " +MAX_PORTFOLIO_SIZE +"stocks ");
	}

	/**
	 * Remove stock from portfolio
	 * @param symbol
	 */

	public  boolean removeStock(String symbol)
	{
		sellStock(symbol,-1);
		for(int i=0; i<stockStatus.length;i++)
			if(symbol.equals(stockStatus[i].getSymbol()))
			{
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

		for(int i=0; i<stockStatus.length; i++)
			if(symbol.equals(stockStatus[i].getSymbol()))
			{
				if(quantity == -1) {
					float amount1=stockStatus[i].getStockQuantity()*stockStatus[i].getBid();
					updateBalance(amount1);
					stockStatus[i].setStockQuantity(0);
				}
				else if(stockStatus[i].getStockQuantity()-quantity < 0){
					System.out.println("Not enough stocks to sell");
				}
				else if (stockStatus[i].getStockQuantity()-quantity >= 0){
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()-quantity);
					float amount = quantity*stockStatus[i].getBid();
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

		for(int i=0; i<stockStatus.length;i++)
			if(symbol.equals(stockStatus[i].getSymbol()))
			{
				if( quantity == -1) {
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+ (int)(balance/stockStatus[i].getAsk()));
					float spent = ((int)(balance/stockStatus[i].getAsk()) *stockStatus[i].getAsk())/(-1); //how much bought
					updateBalance(spent);

				}
				else{
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+quantity);
					float spent1=(quantity*stockStatus[i].getAsk())/(-1);
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
			stockStr += stockStatus[i].getHtmlDescription() + "<br/>";


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
			sum+=stockStatus[i].getStockQuantity() * stockStatus[i].getBid();

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
	
	//Setters

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

	public float getBalance()
	{
		return balance;
	}
}