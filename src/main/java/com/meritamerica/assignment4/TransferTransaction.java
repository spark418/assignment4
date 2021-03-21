package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction{
	public double amount;

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		this.targetAccount = targetAccount;
		setTargetAccount(targetAccount);
		setSourceAccount(sourceAccount);
		this.amount = amount;
		setAmount(amount);
	}


	public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (getAmount() < 0) {
			setRejectionReason("You enter a negative amount " + getAmount());
			throw new NegativeAmountException("You enter a negative amount " + getAmount());
			
		} else if (getAmount() < targetAccount.getBalance()) {
			setRejectionReason("Not enough money in target amount"  + getAmount());
			throw new ExceedsAvailableBalanceException("Not enough money in target amount " + getAmount());
			
		} else if(getAmount() > 1000) {
			setRejectionReason("Amount is $1000 or more " + getAmount());
			setProcessedByFraudTeam(true);
			throw  new ExceedsFraudSuspicionLimitException("Amount is $1000 or more " + getAmount());
			
		} else {
			sourceAccount.withdraw(getAmount());
			targetAccount.deposit(getAmount());
		}
	}
}
