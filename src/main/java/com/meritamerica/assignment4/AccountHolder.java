package com.meritamerica.assignment4;

import java.io.IOException;

public class AccountHolder implements Comparable<AccountHolder>
{
	private static final double MAX_BALANCE_AMOUNT = 250000;

	private  String firstName = "";
	private  String middleName = "";
	private  String lastName = "";
	private  String ssn = "";
	
	private CheckingAccount[] amountCheckingAccounts = new CheckingAccount[0];
	private CheckingAccount clientCheckingAccount;
	private double checkingBalance ;

	private SavingsAccount[] amountSavingsAccounts = new SavingsAccount[0];
	private SavingsAccount clientSavingsAccount;
	private double savingsBalance ;

	private CDOffering cdOffering = new CDOffering(0,0);

	private CDAccount[] amountCDAccounts = new CDAccount[0];
	private CDAccount cdAccount = new CDAccount(cdOffering,0);
	private double cdBalance = 0;

	private double combinedBalance ;

	private int counterC = 0;
	private int counterS = 0;
	private int counterCD = 0;

	private  double totalB = 0;


	public AccountHolder(){	

	}
	
	 //AccountHolder Constructor (String, String, String, String)
	 
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) 
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;

	}


	//Getters

		public String getFirstName() 
	{
		return this.firstName;
	}

	
	public String getMiddleName()
	{
		return this.middleName;
	}
	
	public String getLastName() 
	{
		return this.lastName;
	}
	
	public String getSSN() 
	{
		return this.ssn;
	}

	//Setters

	public void setFirstName(String firstName) 
	{
		this.firstName= firstName; 
		
	}
	public void setMiddleName(String middleName) 
	{
		this.middleName = middleName;
	}
	

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
		
	}


	public void setSSN(String ssn) 
	{
		this.ssn = ssn;
	}

	//Checking Account
	
	//Creates a checking account and calls addCheckingAccount(CheckingAccount) method
	public CheckingAccount addCheckingAccount(double openingBalance)throws ExceedsCombinedBalanceLimitException

	{
		
		this.clientCheckingAccount = new CheckingAccount(openingBalance);
		addCheckingAccount(this.clientCheckingAccount);
		return this.clientCheckingAccount;
	}

	//Receives a checkingAccount and stores it in an CheckingAccount[] 
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException

	{
		totalB  = totalB + 	checkingAccount.getBalance();
		if (totalB < MAX_BALANCE_AMOUNT) {
			if(counterC == this.amountCheckingAccounts.length) {
				CheckingAccount[] newCheckingAccounts = new CheckingAccount[counterC + 1];
				for(int i = 0 ; i < counterC  ; i++) {
					newCheckingAccounts[i] = this.amountCheckingAccounts[i];
				} 
				this.amountCheckingAccounts = newCheckingAccounts;
			}			
			this.amountCheckingAccounts[counterC] = checkingAccount;
			this.counterC++; 
			return null;
		} else 
			return null;
	}

	// Returns CheckingAccount[] 
	public CheckingAccount[] getCheckingAccounts() 
	{
		return this.amountCheckingAccounts;
	}

	// Returns the total amount of checkingAccounts
	public int getNumberOfCheckingAccounts() 
	{
		return this.amountCheckingAccounts.length;
	}

	// Returns the collective Balance between all checkingAccounts
	// that are stored in a CheckingAccount[] 
	public double getCheckingBalance() 
	{
		double checkingBalance = 0;
		for (int i = 0; i < this.amountCheckingAccounts.length  ; i++) 
		{
			checkingBalance = this.amountCheckingAccounts[i].getBalance() 
					+checkingBalance;
		}
		this.checkingBalance = checkingBalance;
		return this.checkingBalance;

	}

	//Savings Account
	// Creates a savings account and calls addSavingsAccount(SavingsAccount) method
	public SavingsAccount addSavingsAccount(double openingBalance)throws ExceedsCombinedBalanceLimitException

	{
		this.clientSavingsAccount = new SavingsAccount(openingBalance);
		addSavingsAccount(this.clientSavingsAccount);
		return this.clientSavingsAccount;
	}
	//Receives a savingsAccount and stores it into a SavingsAccount[]
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount)throws ExceedsCombinedBalanceLimitException

	{
		totalB = totalB + savingsAccount.getBalance() ;
		
		if(totalB < MAX_BALANCE_AMOUNT) 
		{
			if(counterS == this.amountSavingsAccounts.length) 
			{
				SavingsAccount[] newSavingsAccount = new SavingsAccount[counterS + 1];
				for(int i = 0; i < counterS ; i++) {
					newSavingsAccount[i] = this.amountSavingsAccounts[i];
				}
				this.amountSavingsAccounts = newSavingsAccount;
			}
			this.amountSavingsAccounts[counterS] = savingsAccount;
			this.counterS++;
			return null;

		} else {
			return null;
		}	
	}

	 //Returns a SavingsAccount[]
	public SavingsAccount[] getSavingsAccounts()
	{
		return this.amountSavingsAccounts;
	}

	//Returns the total amount of savingsAccounts
	public int getNumberOfSavingsAccounts() 
	{
		return this.amountSavingsAccounts.length;
	}

	//Returns the collective Balance between all savingsAccounts
	//that are stored in a SavingsAccount[] 
	public double getSavingsBalance() 
	{
		double savingsBalance = 0;
		for (int i = 0; i < this.amountSavingsAccounts.length; i++) 
		{
			savingsBalance = this.amountSavingsAccounts[i].getBalance() 
					+ savingsBalance;
		}
		this.savingsBalance = savingsBalance;
		return this.savingsBalance;

	}

	//CD Account

	//Creates a CD Account
	 //@throws ExceedsFraudSuspicionLimitException 
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) throws ExceedsFraudSuspicionLimitException 
	{
	
		if(openingBalance > 1000) {
			throw  new ExceedsFraudSuspicionLimitException("Amount is $1000 or more " );
		} else {
			DepositTransaction d; new DepositTransaction(cdAccount,openingBalance);
			CDAccount cdA = new CDAccount(offering, openingBalance);
			addCDAccount(this.cdAccount);
			return this.cdAccount;
		}
		
	}

	// Adds cdAccount into a CDAccounts[]
	public CDAccount addCDAccount(CDAccount cdAccount) 
	{
		if(counterCD == this.amountCDAccounts.length) 
		{
			CDAccount[] newCDAccount = new CDAccount[counterCD + 1];
			for(int i = 0; i < counterCD; i++) 
			{
				newCDAccount[i] = this.amountCDAccounts[i];
			}
			this.amountCDAccounts = newCDAccount;
			this.amountCDAccounts[counterCD] = cdAccount;
			counterCD ++;
		}
		//add deposit with opening balance
		return null ;
	}
	// Returns a CDAccounts[]
	public CDAccount[] getCDAccounts() 
	{
		return this.amountCDAccounts;
	}
	//Returns the amount of CDAcounts
	public int getNumberOfCDAccounts() 
	{
		return this.amountCDAccounts.length;
	}
	//Returns the combined balance of all CD Accounts
	public double getCDBalance() {
		double cdB = 0;
		for (int i = 0; i < this.amountCDAccounts.length ; i++) 
		{
			cdB = this.amountCDAccounts[i].getBalance()
					+ cdB ;
		}
		this.cdBalance = cdB;
		return this.cdBalance;
	}
	 //Adds Savings Balance, Checking Balance and CD Balance
	public double getCombinedBalance() 
	{
		this.combinedBalance	= getCheckingBalance()+ getSavingsBalance()+ getCDBalance();
		return combinedBalance;
	}

	public  static AccountHolder readFromString(String accountHolderData) 
	{

		String lastName;
		String middleName;
		String firstName;
		String ssn;
		AccountHolder ac;
		String[] values = accountHolderData.split(",");

		try 
		{
			lastName = String.valueOf(values[0]);
			middleName = values[1];
			firstName = values[2];
			ssn = values[3];
		} catch (Exception e) {
			throw e;
		}
		ac = new AccountHolder(firstName, middleName, lastName, ssn);

		return ac;
	}

	public String toString()
	{
		String client = "Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\n" + 
				"SSN: " + this.ssn + "\n" +
				"Checkings Balance: $" + getCheckingBalance() + "\n" +
				"Savings Balance: $" + getSavingsBalance() + "\n" +
				"CD Accounts Balance: $" + getCDBalance() + " " +   
				"Total Balance: $" + getCombinedBalance();
		return client;
	}

	public int compareTo(AccountHolder otherAccountHolder) 
	{

		this.combinedBalance = otherAccountHolder.getCombinedBalance();


		if (otherAccountHolder.combinedBalance < otherAccountHolder.combinedBalance) 
		{
			return 1;

		}else

			return -1;
	}
}