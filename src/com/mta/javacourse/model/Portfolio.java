package com.mta.javacourse.model;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.NotEnoughStocksToSellException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;

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

	public void addStock(Stock s1)throws StockAlreadyExistsException, PortfolioFullException
	{
		for(int i=0; i < portfolioSize; i++)
		{
			if(this.stockStatus[i].getSymbol().equals(s1.getSymbol()))
			{
				throw new StockAlreadyExistsException(s1.getSymbol());
			}
		}
		if(portfolioSize < stockStatus.length)
		{
			stockStatus[portfolioSize] = new StockStatus(s1);
			portfolioSize++;
		}

		else
			throw new PortfolioFullException();
	}

	/**
	 * Remove stock from portfolio
	 * @param symbol
	 */

	public  void removeStock(String symbol) throws StockNotExistException, NotEnoughStocksToSellException
	{
		sellStock(symbol,-1);
		for(int i=0; i<stockStatus.length;i++)
			if(symbol.equals(stockStatus[i].getSymbol()))
			{
				stockStatus[i] = stockStatus[portfolioSize-1];
				stockStatus[portfolioSize-1] =null;
				portfolioSize--;
			}
			else
				throw new StockNotExistException(symbol);
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

	public void sellStock(String symbol, int quantity ) throws StockNotExistException, NotEnoughStocksToSellException
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
					throw new NotEnoughStocksToSellException();
				}
				else if (stockStatus[i].getStockQuantity()-quantity >= 0){
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()-quantity);
					float amount = quantity*stockStatus[i].getBid();
					updateBalance(amount);
				}
			}
			else
				throw new StockNotExistException(symbol);
	}

	/**
	 * Buy stocks and update the balance
	 * @param symbol
	 * @param quantity
	 * @return
	 */

	public void buyStock(String symbol, int quantity ) throws StockNotExistException, BalanceException
	{

		for(int i=0; i<stockStatus.length;i++)
			if(symbol.equals(stockStatus[i].getSymbol()))
			{
				if( quantity == -1)
				{
					float spent = ((int)(balance/stockStatus[i].getAsk()) *stockStatus[i].getAsk())/(-1); //how much bought
					if (spent/(-1) > balance)
						throw new BalanceException();
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+ (int)(balance/stockStatus[i].getAsk()));
					updateBalance(spent);
				}
				else
				{
					float spent1=(quantity*stockStatus[i].getAsk())/(-1);
					if (spent1/(-1) > balance)
						throw new BalanceException();
					stockStatus[i].setStockQuantity(stockStatus[i].getStockQuantity()+quantity);
					updateBalance(spent1);
				}
			}
		throw new StockNotExistException(symbol);
	}

	/**
	 * Method that returns HTML description in bold font with break between lines
	 * @return
	 */

	public String getHtmlString() 
	{

		String stockStr= "<h1><b><center><font face=tahoma><span style=background-color:#99CCFF>Exercise 08- My portfolio</span style=background-color:#99CCFF><font face=tahoma></center></b></h1>";

		stockStr +="<b>Total Portfolio Value: </b>" + getTotalValue() +"$ <b>Total Stocks Value: </b>"+ getStocksValue() + "$ <b>Balance: </b>"+ getBalance() +"$ <br/><br/></h1>";

		for(int i=0; i<portfolioSize; i++)
			stockStr += stockStatus[i].getHtmlDescription() + "<br/></font face>";

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
