package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount{
	private CDOffering myOffering = null; 
	
	
	
	public CDAccount(CDOffering offering, double balance) {
		this.myOffering = offering;
		super.balance = balance;
	}
	
	public CDAccount(CDOffering offering, double balance, long accountNumber, Date openedOn) {
	   this.myOffering = offering;
	   super.balance = balance;
	   super.accountNumber = accountNumber;
	   super.openedOn = openedOn;
	   
	}

	public  double getBalance() {
		return super.getBalance();
	}
	
	public double getInterestRate() {
		return myOffering.getInterestRate();
		
	}
	
	public int getTerm() {
		System.out.println("Igothere");
		return myOffering.getTerm();
	}
	
	public Date getStartDate(){
		return super.openedOn;
	}
	
	public long getAccountNumber() {
		return super.getAccountNumber();
	}
	
	public double futureValue() {
		futureValue = balance * (Math.pow((1 + myOffering.getInterestRate()),myOffering.getTerm()));
		return futureValue;
	}
	
	@Override
	public boolean withdraw(double amount) {
		boolean success = false;
		if(2020 > 2020 + myOffering.getTerm()) {
			if (super.getBalance() > amount) {
				super.balance = balance - amount;
				success = true;
			}
		}
		return success;
		
	}
	@Override
	public boolean deposit(double amount) {
		boolean success = false;
		
		if(2020 > 2020 + myOffering.getTerm()) {
			if(amount < 0) {
				success = false;
				return success;
				
			} else {
				
				super.balance = balance + amount;
				success = true;
				return success;
				
			}
		}
		
		return success;
	}
		
	public static CDAccount readFromString(String accountData) throws ParseException{
		
		String delimiter = ",";
		CDAccount tempAccount = null;
		CDOffering tempOffering = null;

			String[] attributes = accountData.split(delimiter);
			
			long readNumber = Long.valueOf(attributes[0]);
			double readBalance = Double.valueOf(attributes[1]);
			double readInterestRate = Double.valueOf(attributes[2]);
			
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(attributes[3]);
			Date readOpenedOn = date1;
			
			int readTerm = Integer.valueOf(attributes[4]);
			
			tempOffering = new CDOffering(readTerm, readInterestRate);
			tempAccount = new CDAccount(tempOffering, readBalance, readNumber, readOpenedOn);

		
		return tempAccount;
	}

	@Override
	public String writeToString() {
		return accountNumber + "," + balance
				+ "," + myOffering.getInterestRate() + "," + myOffering.getTerm() + "," + openedOn;
	}
	
	
	
		
}