package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * Class represent StockStatus inherit from stock
 * @author Yana Lukashik
 * January 2015
 */

public class StockStatus extends Stock{
	
	private  ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/**
	 * Constructor StockStatus
	 */
	
	public StockStatus()
	{
		super();
		setRecommendation(null);
		setStockQuantity(0);
	}
	
	/**
	 * Constructor that build StockStatus from Stock
	 * @param s1
	 */
	
	public StockStatus(Stock s1)
	{
		super(s1);
		setRecommendation(null);
		setStockQuantity(0);	
	}
	
	/**
	 * Copy constructor
	 * @param s1
	 */
	
	public StockStatus(StockStatus s1)
	{
		super(s1);
		setRecommendation(s1.getRecommendation());
		setStockQuantity(s1.getStockQuantity());
	}
	
	//Setters

	public void setRecommendation(ALGO_RECOMMENDATION recommendation)
	{
		this.recommendation = recommendation;
	}
	
	public void setStockQuantity(int stockQuantity)
	{
		this.stockQuantity = stockQuantity;
	}
	
	//Getters

	public int getStockQuantity()
	{
		return stockQuantity;
	}

	public ALGO_RECOMMENDATION getRecommendation()
	{
		return recommendation;
	}
}