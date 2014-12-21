package com.mta.javacourse.model;

import java.util.Date;

/**
 *Class represent Stock
 *@author Yana Lukashik
 *December 2014
 */

public class Stock {
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
	/**
	 * constructor Stock
	 * @param symbol
	 * @param ask
	 * @param bid
	 * @param date
	 */

	public Stock(String symbol, float ask, float bid, Date date)
	{
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setDate(date);
	}
	
	/**
	 * copy constructor Stock
	 * @param stock
	 */

	public Stock(Stock stock)
	{
		setSymbol(stock.getSymbol());
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		setDate(stock.getDate());
	}
	
	//Getters

	public String getSymbol() {
		return symbol;
	}

	public float getAsk() {
		return ask;
	}
	
	public float getBid() {
		return bid;
	}
	
	public Date getDate() {
		return date;
	}
	
	//Setters
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 *Method that returns HTML description in bold font with break between lines
	 */
	
	public String getHtmlDescription() {
		String stockHtmlDetailsString=  "<b>Stock Symbol</b>: " +getSymbol()+ " <b>Bid</b>: " +getBid()+ "$ <b>Ask</b>: " +getAsk()+ "$ <b>Date</b>: " +getDate();
		return stockHtmlDetailsString;
	}

}
