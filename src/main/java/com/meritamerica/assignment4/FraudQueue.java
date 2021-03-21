package com.meritamerica.assignment4;

public class FraudQueue {
	
	Transaction t;
	
	FraudQueue(){
		
	}
	
	public void addTransaction(Transaction transaction) {
		this.t = transaction;
	}
	public Transaction getTransaction() {
		return t;
		
	}

}