package com.meritamerica.assignment4;

public class ExceedsFraudSuspicionLimitException extends Exception{

	public ExceedsFraudSuspicionLimitException(String e) {
	
		super (e);
	
			System.out.println("cannot deposit more than 1000");
	}
	
}