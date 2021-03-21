package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Date;

public class CDAccount extends BankAccount {

	private static double balance;
	private CDOffering offering;
	private static int term;
	private static double interestRate;
	private static String[] array = new String[5];
	private static ArrayList<String> tran = new ArrayList<String>();
	private static Date date;
	private  static long accountNumber;
	 
	
	
	
	CDAccount(){
		super(accountNumber, balance, interestRate, date);
	}
	CDAccount(long accountNumber, double balance, double interestRate, Date date, int term){
		super(accountNumber, balance, interestRate, date);
	}
	CDAccount(CDOffering offering, double balance){
		super(MeritBank.getNextAccountNumber(),balance,offering.getInterestRate());
		
		this.offering = offering;
		this.term= this.offering.getTerm();
		this.interestRate = this.offering.getInterestRate();
		this.balance = balance;
	}
	
	//Need to override deposit and withdraw.
	
	public static CDAccount readFromString(String accountData) {
		
		
		CDAccount cd = new CDAccount();
		String[] trans = accountData.split(",");
		try {
				
					
				accountNumber =    Long.parseLong(trans[0]);
				balance =      Double.parseDouble(trans[1]);
				interestRate = Double.parseDouble(trans[2]);
				date =       cd.dateAccountOpened(trans[3]);
				term =           Integer.parseInt(trans[4]);
			
		} catch (NumberFormatException e) {
			throw e;
		}
		
		CDOffering offering = new CDOffering();
		cd = new CDAccount(accountNumber, balance, interestRate, date, term);
		
		
		System.out.println("Account: " + accountNumber + "\n" +
				"Balance: " + balance + "\n" + 
				"Interest Rate: " + interestRate + "\n" + 
				"Date: " + date + "\n" + 
				"Term: " + term);
		
		return cd;
	}
	
	public  int getTerm() {
		return term
				;
	}
	
	@Override
	public boolean deposit(double amount) {
		return false;
	}
	public boolean withdraw (double amount) {
		return false;
	}
	
	public double futureValue() { //overriding term because could not figure out how to pass the term from offering to my variables in bankAccount
		double fV  = balance * Math.pow((1+ interestRate ), term);
		return fV;
	}
	@Override
	public String toString() {
		return "CDAccount [balance=" + balance + ", Offering Term =" + offering.getTerm() + "]";
	}

	
}