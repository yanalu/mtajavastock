package com.mta.javacourse.exception;

/**
 * Negative balance Exception
 * @author yana
 * January 2015
 */

public class BalanceException extends Exception{
	
	public BalanceException()
	{
		super("You don`t have enough money");
	}
}
