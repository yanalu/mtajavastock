package com.mta.javacourse;

import java.util.Date;

public class Stock {
	private String symbol;
	private float ask;
	private float bid;
	private Date date;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription() {
		String stockHtmlDetailsString=  "<b>Stock Symbol</b>: " +getSymbol()+" <b>Bid</b>: " +getBid()+ " <b>Ask</b>: " +getAsk()+ " <b>Date</b>: " +getDate();
		return stockHtmlDetailsString;
	}

}
