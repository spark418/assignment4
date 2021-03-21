package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction{
	
	WithdrawTransaction(BankAccount targetAccount, double amount){
		super(targetAccount, amount);
		
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
			success = sourceAccount.withdraw(amount);
			if(success == false) {
				throw new NegativeAmountException("Cannot Deposit Negative Amount.");
			}
		}else
		{
			throw new ExceedsAvailableBalanceException("e");
		}
		
	}
}