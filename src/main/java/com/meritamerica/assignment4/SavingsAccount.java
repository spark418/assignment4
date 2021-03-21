package com.meritamerica.assignment4;

import java.util.Date;

public class SavingsAccount extends BankAccount{
	
	
	
	
	private static Date date;
	private static long accountNumber; 
	private static double balance;
	private static double interestRate = 0.01 ;
	
	SavingsAccount() {
		super(MeritBank.getNextAccountNumber(),balance, interestRate);
	}
	SavingsAccount(double openingBalance) {
		super(MeritBank.getNextAccountNumber(),openingBalance, interestRate);
	}
	SavingsAccount(long accountNumber, double balance, double interestRate, Date date) {
		super(accountNumber,balance, interestRate, date);
	}
	
	public static SavingsAccount readFromString(String accountData) {
		double balance;
		double interestRate;
		SavingsAccount savings = new SavingsAccount();

		try {

			String[] values = accountData.split(","); 

			accountNumber =    Long.parseLong(values[0]);
			balance =      Double.parseDouble(values[1]);
			interestRate = Double.parseDouble(values[2]);
			date =  savings.dateAccountOpened(values[3]);
		} catch(NumberFormatException e){ 
			throw e;					  
		}
	
		savings = new SavingsAccount(accountNumber, balance, interestRate, date);
		
		return savings;

	}
}