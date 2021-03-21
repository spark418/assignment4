package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount{
	
	private double interestRate = .0001;
	
	CheckingAccount(double openingBalance){
		super.balance = 0;
		super.interestRate = interestRate;
	}
	CheckingAccount(long accountNumber, double openingBalance, double interestRate, Date openedOn){
		super.accountNumber = accountNumber;
		super.balance = openingBalance;
		super.interestRate = interestRate;
		super.openedOn = openedOn;
	}
	
	public long getAccountNumber() {
		return super.getAccountNumber()	;
	}
	
	public Date getOpenedOn() {
		return super.getOpenedOn();
	}
	
	public double getBalance() {
		return super.getBalance();
	}
	
	public double getInterestRate() {
		return super.getInterestRate();
	
	}
	
	public double futureValue(int years) {
		return MeritBank.recursiveFutureValue(balance, years, interestRate);
		
	}
	

	public String toString() {
		
		return "checking acccount balance: $" + balance
				+ "\n" + "checking account interest rate: " + interestRate 
				+ "\n" + "checking account balance in 3 years: $"+ futureValue(3);
				
	}
	
	public static CheckingAccount readFromString(String accountData) throws ParseException{
		
		String delimiter = ",";
		CheckingAccount tempAccount = null;
		
			String[] attributes = accountData.split(delimiter);
			
			
			long readNumber = Long.valueOf(attributes[0]);
			double readBalance = Double.valueOf(attributes[1]);
			System.out.println(readBalance);
			double readInterestRate = Double.valueOf(attributes[2]);
			
			
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(attributes[3]);
			Date readOpenedOn = date1;
			
			tempAccount = new CheckingAccount(readNumber, readBalance, readInterestRate, readOpenedOn);

		
		return tempAccount;
	}
	
	
	


}