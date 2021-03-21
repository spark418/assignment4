package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract class BankAccount {
	
	private double  balance ;
	private double interestRate;
	private long accountNumber;
	private double futureValue;
	private double accountTotal;
	private Date date;
	private int term;
	List<Transaction> transactions;
	
	
	 BankAccount(double balance, double interestRate){
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	 BankAccount(long accountNumber, double balance, double interestRate){
		 this.accountNumber = accountNumber;
		 this.balance = balance;
		 this.interestRate = interestRate;
	 }
	BankAccount(long accountNumber, double balance, double interestRate, Date date){
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.date= date;
	}
	

	public long getAccountNumber() 
	{
		return this.accountNumber;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	public double getInterestRate() 
	{
		return this.interestRate;
	}
	
	public boolean withdraw(double amount)
	{
		if((this.balance - amount) >= 0) 
		{
			this.balance = this.balance - amount;
			return true;
		} else
			{
			
			return false;
			}	
	}
	
	public boolean deposit(double amount) 
	{
		if(((this.balance + amount) <= 250000) && amount > 0)
		{
			this.balance = this.balance + amount;
			return true;
		} else 
			return false;	
	}
	
	
	public double futureValue(int term) 
	{
		this.futureValue = this.balance * Math.pow((1+ interestRate ), term);
		return this.futureValue;
		
	}

	
	//public date
	
	public  Date dateAccountOpened(String string)
	{
			try 
			{
				DateFormat startDate = new SimpleDateFormat("dd/MM/yyyy"); //sets format
				Date date = (Date)startDate.parse(string); //converts to date
	        	this.date = date;
	        	return this.date;				
			} catch(ParseException e)
			{
				System.out.println();
			}
			return this.date;
	}
	
	public  Date getOpenedOn()
	{
		return this.date;
	}
	
	//ASssignment4===
	
	
	public void addTransaction(CDAccount cdA){
		if(MeritBank.processTransaction(true)) {}
		transactions.addAll((Collection<? extends Transaction>) cdA);	
		
		
	}
	
	public List<Transaction>getTransactions(){
		return transactions;
	}
	
	public static BankAccount readFromString()
	{
		return null;
	}
	
	public String writeToString()
	{
		return"";
	}
	public String toString() 
	{
		return "";
	}
	
		
}