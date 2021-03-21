package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{
	
	
	
	DepositTransaction(BankAccount targetAccount, double amount) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException{
		super(targetAccount, amount);
		
			process();
		
	}
	
	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
		boolean success = false;
		success = isProcessedByFraudTeam();
		if(success == true) {
			throw new ExceedsFraudSuspicionLimitException("Exceeds Fraud Suspicion Limit");
		}
		if(amount > 0) {
			success = targetAccount.deposit(amount);
			if(success == false) {
				throw new NegativeAmountException("Cannot Deposit Negative Amount.");
			}
		}else
		{
			throw new ExceedsAvailableBalanceException("e");
		}
		
		
		
	}
}