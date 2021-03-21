package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{


	DepositTransaction(BankAccount targetAccount, double amount){
		this.targetAccount = targetAccount;
		setTargetAccount(targetAccount);
		this.amount = amount;
		
	}

	public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (amount < 0) {
			setRejectionReason("You enter a negative amount " + amount);
			throw new NegativeAmountException("You enter a negative amount " + amount);
			
		} else if (getAmount()  > amount ) {
			setRejectionReason("Not enough money in target amount"  + amount);
			throw new ExceedsAvailableBalanceException("Not enough money in target amount " + amount);
			
		} else if(amount > 1000) {
			setRejectionReason("Amount more then $1000 " + amount);
			setProcessedByFraudTeam(true);
			throw  new ExceedsFraudSuspicionLimitException("Amount is $1000 or more " + amount);
			
		} else { 
			targetAccount.deposit(getAmount() + amount);
		}
	}
	
}