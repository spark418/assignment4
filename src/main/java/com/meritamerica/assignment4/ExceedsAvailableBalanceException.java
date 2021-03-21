package com.meritamerica.assignment4;

public class ExceedsAvailableBalanceException  extends Exception{

	
	public ExceedsAvailableBalanceException(String e) {
		
		super (e);
		System.out.println("not enough balance");
		
		
	}
	
	
}

