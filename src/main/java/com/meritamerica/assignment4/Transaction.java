package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {
	static BankAccount sourceAccount;
	static BankAccount targetAccount;
	static double amount;
	static java.util.Date date;
	boolean isProcessed;
	String reason;
	
	public Transaction(BankAccount targetAccount, double amount) {
		Transaction.targetAccount = targetAccount;
		Transaction.amount = amount;
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		Transaction.sourceAccount = sourceAccount;
		Transaction.targetAccount = targetAccount;
		Transaction.amount = amount;
	}

	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		Transaction.sourceAccount = sourceAccount;
	}
	
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		Transaction.targetAccount = targetAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		Transaction.amount = amount;
	}
	
	public java.util.Date getTransactionDate(){
		return date;
	}
	
	public void setTransactionDate(java.util.Date date) {
		Transaction.date = date;
	}
	
	public String writeToString() {
		return sourceAccount + "," + targetAccount + "," + amount + "," + date;
	}
	
	public static Transaction readFromString(String transactionDataString) {
	
		
		String delimiter = ",";
		
			String[] attributes = transactionDataString.split(delimiter);
			
			if(attributes[0].equals("-1")) {
				sourceAccount = null;
			}else {
				sourceAccount.accountNumber = Long.valueOf(attributes[0]);
			}
			
				targetAccount.accountNumber = Long.valueOf(attributes[1]);
				amount = Double.parseDouble(attributes[2]);
			
			
				Date date1;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(attributes[3]);
					date = date1;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return null;
	}
	
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public boolean isProcessedByFraudTeam() {
		if(amount > 1000) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	public String getRejectionReason() {
		return reason;
	}
	
	public void setRejectionReason(String reason) {
		this.reason = reason;
	}

}