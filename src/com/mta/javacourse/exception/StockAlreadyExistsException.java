package com.mta.javacourse.exception;

/**
 * Stock already exists Exception
 * @author yana
 * January 2015
 */

public class StockAlreadyExistsException extends Exception{
	
	public StockAlreadyExistsException(String symbol)
	{
		super("Stock " +symbol+ " already exists");
	}

}
