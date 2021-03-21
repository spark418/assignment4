package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class BankAccount {
	
	public static long accountNumber;
	public static double balance;
	public static double interestRate = .01;
	double futureValue;
    static Date openedOn;
    ArrayList<Transaction> tList = new ArrayList<Transaction>();
    
    public BankAccount() {
    	
    }
	
	public BankAccount(double balance, double interestRate) {
		BankAccount.balance = balance;
		BankAccount.interestRate = interestRate;
	}
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn)
	{
		BankAccount.accountNumber = accountNumber;
		BankAccount.balance = balance;
		BankAccount.interestRate = interestRate;
		BankAccount.openedOn = accountOpenedOn;
		
	}
	
	public void addTransaction(Transaction transaction) {
		tList.add(transaction);
	}
	
	public List<Transaction> getTransactions(){
		return tList;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public boolean withdraw(double amount) {
		boolean success = false; 
		if (BankAccount.balance > amount) {
			balance = balance - amount;
			success = true;
		}
		return success;
		
	}
	
	public boolean deposit(double amount) {
		if(amount<0.0) {
			//System.out.println("Cannot deposit a negative amount.");
			return false;
			
		} else {
			balance = balance + amount;
			return true;
		}
		
		
	}
	
	public double futureValue(int years) {
		
		return MeritBank.recursiveFutureValue(balance, years, interestRate);
	}
	
	public Date getOpenedOn() {
		return openedOn;
	}
	
	public static BankAccount readFromString(String accountData) throws ParseException{
		
		String delimiter = ",";
		//BankAccount tempAccount = null;

			String[] attributes = accountData.split(delimiter);
			
			accountNumber = Long.valueOf(attributes[0]);
			balance = Double.valueOf(attributes[1]);
			interestRate = Double.valueOf(attributes[2]);
			
			
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(attributes[3]);
			openedOn = date1;
			
			//tempAccount = new BankAccount(readNumber, readBalance, readInterestRate, readOpenedOn);
			

		
		return null;
	}
	

	public String writeToString() {
		return accountNumber + "," + balance + "," + interestRate + "," + openedOn;
	}

}