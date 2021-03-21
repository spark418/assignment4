package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends BankAccount{

	private double interestRate = .01;
	SavingsAccount(double openingBalance){
		super.balance = 0;
		super.interestRate = interestRate;
	}
	
	SavingsAccount(long accountNumber, double openingBalance, double interestRate, Date openedOn){
		super.accountNumber = accountNumber;
		super.balance = openingBalance;
		super.interestRate = interestRate;
		super.openedOn = openedOn;
	}
	
	public long getAccountNumber() {
		return super.getAccountNumber();
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
	
	public static SavingsAccount readFromString(String accountData) throws ParseException{
		
		String delimiter = ",";
		SavingsAccount tempAccount = null;
		
			String[] attributes = accountData.split(delimiter);
			
			long readNumber = Long.valueOf(attributes[0]);
			double readBalance = Double.valueOf(attributes[1]);
			double readInterestRate = Double.valueOf(attributes[2]);
			
			
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(attributes[3]);
			Date readOpenedOn = date1;
			
			tempAccount = new SavingsAccount(readNumber, readBalance, readInterestRate, readOpenedOn);

		
		return tempAccount;
	}
	
	
	public String toString() {
		return "savings account balance: $" + balance
		+ "\n" + "savings account interst rate: " + interestRate 
		+ "\n" + "savings account balance in 3 years: $" + futureValue(3);
	}
}