package com.mta.javacourse.exception;

/**
 * Stock not exists Exception
 * @author yana
 * January 2015
 */

public class StockNotExistException extends Exception{
	
	public StockNotExistException(String symbol)
	{
		super("Stock " +symbol+ " not exists");
	}

}
