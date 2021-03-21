package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction{
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		super(sourceAccount, targetAccount, amount);
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		boolean success = false;
		success = isProcessedByFraudTeam();
		if(success == true) {
			throw new ExceedsFraudSuspicionLimitException("Exceeds Fraud Suspicion Limit");
		}
		if(amount < 0) {
			if(!sourceAccount.withdraw(amount)){
				throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
			}
		}
		else {
			
			success = sourceAccount.deposit(amount);
			if(success == false) {
				throw new NegativeAmountException("Cannot Deposit Negative Amount.");
			}
		}
			
		
	}
}