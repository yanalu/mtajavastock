package com.mta.javacourse.exception;

/**
 * Portfolio full Exception
 * @author yana
 * January 2015
 */

public class PortfolioFullException extends Exception{
	
	public PortfolioFullException()
	{
		super("Portfolio is full");
	}

}
