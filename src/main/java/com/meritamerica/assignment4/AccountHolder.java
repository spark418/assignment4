package com.meritamerica.assignment4;

	import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
  

public class AccountHolder implements Comparable<AccountHolder>{
	private String firstName;
	private String middleName;
	private String lastName;
	private String SSN;
	private double openingBalance;
	private CheckingAccount[] checkingAccounts = new CheckingAccount[100];
	private int numberOfCheckingAccounts = 0;
	private int numberOfSavingsAccounts = 0;
	private int savingsIndex = 0;
	private int checkingIndex = 0;
	private SavingsAccount[] savingsAccounts = new SavingsAccount[100];
	private int numberOfCDAccounts = 0;
	public CDAccount[] cdAccounts = new CDAccount[5];
	
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.SSN = ssn;
		
	}
	
	public void setNumberOfCheckingAccounts(int num) {
		this.numberOfCheckingAccounts = num;
	}
	
	public void setNumberofSavingsAccounts(int num) {
		this.numberOfSavingsAccounts = num;
	}
	
	public void setNumberofCDAccounts(int num) {
		this.numberOfCDAccounts = num;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
		
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSSN() {
		return SSN;
		
	}
	
	public void setSSN(String ssn) {
		this.SSN = ssn;
	}
	
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException{
		this.openingBalance = openingBalance;
		
		if((this.openingBalance + getCheckingBalance() + getSavingsBalance()) < 250000) {
			numberOfCheckingAccounts++;
			
			if(numberOfCheckingAccounts > checkingAccounts.length) {
				CheckingAccount[] checkingAccountsTemp = Arrays.copyOf(checkingAccounts, checkingAccounts.length+5);
				checkingAccounts = checkingAccountsTemp;
			}
			
			checkingAccounts[numberOfCheckingAccounts-1] = new CheckingAccount(openingBalance);
			try {
				checkingAccounts[numberOfCheckingAccounts-1].addTransaction(new DepositTransaction(checkingAccounts[numberOfCheckingAccounts - 1], openingBalance));
			} catch (NegativeAmountException | ExceedsAvailableBalanceException
					| ExceedsFraudSuspicionLimitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return checkingAccounts[numberOfCheckingAccounts-1];
			
		}
		throw new ExceedsCombinedBalanceLimitException("Exceeds Combined Balance Limit");
	}
			
			//incomplete method
		public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
			if((getCheckingBalance() + getSavingsBalance()) < 250000) {	
				if(savingsAccounts[checkingIndex] == null) {
					checkingAccounts[checkingIndex++] = new CheckingAccount(checkingAccount.getBalance());
				}
					
				try {
					checkingAccounts[numberOfCheckingAccounts-1].addTransaction(new DepositTransaction(checkingAccounts[numberOfCheckingAccounts - 1], openingBalance));
				} catch (NegativeAmountException | ExceedsAvailableBalanceException
						| ExceedsFraudSuspicionLimitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}else {
				throw new ExceedsCombinedBalanceLimitException("Exceeds Combined Balance Limit");
			}
				
				
				
		
				return checkingAccounts [numberOfCheckingAccounts-1];
	}
	
		
	
	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccounts;
		
	}
	
	public int getNumberOfCheckingAccounts() {
		return numberOfCheckingAccounts;
		
	}
	public double getCheckingBalance() {
		
		double balance = 0;
		
		for(int i=0; i<checkingAccounts.length; i++)
		{
			
			if(checkingAccounts[i] == null) {
				return balance; 
			}
			else {
				balance += checkingAccounts[i].getBalance();
			}
			
		}
		
		return balance;
		
	}
	
	
	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException{
		this.openingBalance = openingBalance;
		
		if((openingBalance + getCheckingBalance() + getSavingsBalance()) < 250000) {
			numberOfSavingsAccounts++;
			
			if(numberOfSavingsAccounts > savingsAccounts.length) {
				SavingsAccount[] SavingsAccountsTemp = Arrays.copyOf(savingsAccounts, savingsAccounts.length+5);
				savingsAccounts = SavingsAccountsTemp;
			}
			
			savingsAccounts[numberOfSavingsAccounts-1] = new SavingsAccount(openingBalance);
			try {
				savingsAccounts[numberOfSavingsAccounts-1].addTransaction(new DepositTransaction(savingsAccounts[numberOfSavingsAccounts - 1], openingBalance));
			} catch (NegativeAmountException | ExceedsAvailableBalanceException
					| ExceedsFraudSuspicionLimitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return savingsAccounts[numberOfSavingsAccounts-1];
			
		}
		throw new ExceedsCombinedBalanceLimitException("Exceeds Combined Balance Limit");
	}
		
	
	
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException{
		if((getCheckingBalance() + getSavingsBalance()) < 250000) {
			
			if(savingsAccounts[savingsIndex] == null)
			savingsAccounts[savingsIndex++] = new SavingsAccount(savingsAccount.getBalance());
			try {
				savingsAccounts[numberOfSavingsAccounts-1].addTransaction(new DepositTransaction(savingsAccounts[numberOfSavingsAccounts - 1], openingBalance));
			} catch (NegativeAmountException | ExceedsAvailableBalanceException
					| ExceedsFraudSuspicionLimitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}else {
			throw new ExceedsCombinedBalanceLimitException("Exceeds Combined Balance Limit");
		}
		
		
		
		
		return savingsAccounts [numberOfSavingsAccounts-1];


}
	
	public SavingsAccount[] getSavingsAccounts() {
		return savingsAccounts;
		
	}
	
	public int getNumberOfSavingsAccounts() {
		return numberOfSavingsAccounts;
	}
	
public double getSavingsBalance() {
		
		double balance = 0;
		
		for(int i=0; i<savingsAccounts.length; i++)
		{
			
			if(savingsAccounts[i] != null) {
				System.out.println("savings 1: " + savingsAccounts[i].getBalance());
				balance += savingsAccounts[i].getBalance(); 
			}
				
			
			
		}
		
		return balance;
		
	}
	
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		for(int i = 0; i<cdAccounts.length; i++)
		{				
			CDAccount tempCD = new CDAccount(offering, openingBalance);
			DepositTransaction dT;
			try {
				dT = new DepositTransaction(tempCD, tempCD.getBalance());
				tempCD.addTransaction(dT);
			} catch (NegativeAmountException | ExceedsAvailableBalanceException
					| ExceedsFraudSuspicionLimitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			if(cdAccounts [i] == null) {
				cdAccounts [i] = tempCD;
				numberOfCDAccounts++;
				return cdAccounts[i];
			}
			
		}
		
		return null;
	}
	
	public CDAccount addCDAccount(CDAccount cdAccount) {
		for(int i = 0; i<cdAccounts.length; i++) {
			if(cdAccounts [i] == null) {
				cdAccounts [i] = cdAccount;
				DepositTransaction dT;
				try {
					dT = new DepositTransaction(cdAccount, cdAccount.getBalance());
					cdAccounts[i].addTransaction(dT);
				} catch (NegativeAmountException | ExceedsAvailableBalanceException
						| ExceedsFraudSuspicionLimitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				numberOfCDAccounts++;
				return cdAccounts[i];
			}
			
		}	
		return null;
	}
	
	
	public CDAccount[] getCDAccounts() {
		return cdAccounts;
		
	}
	
	public int getNumberOfCDAccounts() {
		return numberOfCDAccounts;
		
	}
	
	public double getCDBalance() {
		double balance = 0;
		
		for(int i=0; i< numberOfCDAccounts; i++)
		{
			if( cdAccounts[i] != null)
			balance += cdAccounts[i].getBalance();
			
		}
		
		return balance;
		
	}
		
	
	public double getCombinedBalance() {
		double total = 0;
		total = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return total;
	}

	@Override
	public int compareTo(AccountHolder o) {
		
		if(this.getCombinedBalance() > o.getCombinedBalance()) {
			return 1;
		}else {
			return -1;
		}
		
	};
	
	public String writeToString() {
		return lastName + "," + middleName + "," + firstName + "," + SSN;
	}

	
	public static AccountHolder readFromString(String accountData) throws Exception{
		String delimiter = ",";
		AccountHolder tempAccount = null;
		
		try {
			String[] attributes = accountData.split(delimiter);
			
			tempAccount = new AccountHolder(attributes[0], attributes[1], attributes[2], attributes[3]);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return tempAccount;
	}
	
}