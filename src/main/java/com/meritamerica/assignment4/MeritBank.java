package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MeritBank {
	
	
	private static int numOfferings;
	private static int numAccountHolders;
	private static AccountHolder[] MeritHolders = new AccountHolder [numAccountHolders];
	private static CDOffering[] MeritCD = new CDOffering[numOfferings];
	private static int nextAccountNum = 0;
	static double bestValue[] = new double[5];
	static double secondBestValue[] = new double[5];
	static int best = 0;
	static int second = 0;
	static FraudQueue fraud = new FraudQueue();
	


	
	public static void addAccountHolder(AccountHolder accountHolder) {
		for (int i =0; i < MeritHolders.length; i++) {
			if(MeritHolders[i] == null) {
				MeritHolders[i] = accountHolder;
				break;
			}
			
		}
	}
	
	public static AccountHolder[] getAccountHolders() {
		return MeritHolders;
	}

	public static CDOffering[] getCDOfferings() {
		return MeritCD;
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		
	
		
		
		for(int i = 0; i<MeritCD.length; i++) {
			
			if(MeritCD[i] != null) {
				
				bestValue[i] = recursiveFutureValue(depositAmount, MeritCD[i].getTerm(), MeritCD[i].getInterestRate());		
			}
		}
		
		double tempBest;

		tempBest = bestValue[0];
		for(int i = 1; i < bestValue.length; i++) {
			if(tempBest < bestValue[i]) {
				tempBest = bestValue[i];
				best = i;
			}
		}
			return MeritCD[best];
	}
	
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if(MeritCD == null) {
			return null;
		}
		for(int i = 0; i<MeritCD.length; i++) {
			
			if(MeritCD[i] != null) {
				
				secondBestValue[i] = recursiveFutureValue(depositAmount, MeritCD[i].getTerm(), MeritCD[i].getInterestRate());		
			}

		}
		
		double tempSecond;
		tempSecond = secondBestValue[0];
		for(int i = 1; i < secondBestValue.length; i++) {
			if(tempSecond < secondBestValue[i]) {
				second = i-1;
			}
		}
		
		
		return MeritCD[second];
		
	}
	
	public static void clearCDOfferings() {
		
		MeritCD = null;
		
		}
	
	public static void setCDOfferings(CDOffering[] offerings) {
			MeritCD = offerings;

		
	}
	
	public static long getNextAccountNumber() {
		return nextAccountNum;

	}
	 public static double totalBalances() {
		 double total = 0;
		 for(int i = 0; i < MeritHolders.length; i++) {
			 
			 if(MeritHolders[i] != null)
			 total = MeritHolders[i].getCombinedBalance();
		 }
		 return total;
	 }
	 
	 public static double recursiveFutureValue(double amount, int years, double interestRate) {
			
			if(years < 1) {
				return amount;
			}
			else {
				double baseAmount = recursiveFutureValue(amount, years - 1, interestRate);
				baseAmount += baseAmount * interestRate;
				return baseAmount;
			}
			
		
		}
	 
	 public static boolean processTransaction(Transaction transaction)throws NegativeAmountException, ExceedsAvailableBalanceException,
	 																		ExceedsFraudSuspicionLimitException{
		 boolean success = false;
		 WithdrawTransaction withdraw;
		 DepositTransaction deposit;
		 
		 
		 success = transaction.isProcessedByFraudTeam();
		 if(success == false) {
			transaction.setProcessedByFraudTeam(success);
			
			if(transaction.getAmount() > 0) {
				withdraw = new WithdrawTransaction(transaction.getSourceAccount(), transaction.getAmount());
				withdraw.process();
				return true;
			}else if(transaction.getAmount() > 0){
				deposit = new DepositTransaction(transaction.getSourceAccount(), transaction.getAmount());
				deposit.process();
				return true;
			}
		 }
		 else {
			 fraud.addTransaction(transaction);
			 throw new ExceedsFraudSuspicionLimitException("Transaction exceeds $1000");
		 }
		 return false;
	 }
	 
	 public static FraudQueue getFraudQueue() {
		 return fraud;
	 }
	 
	 public static BankAccount getBankAccount(long accountId) {
		 for(int i = 0; i < MeritHolders.length; i++) {
			 for(int k = 0; k < MeritHolders[i].getCheckingAccounts().length; k++)
			 if(MeritHolders[i].getCheckingAccounts()[k].accountNumber == accountId) {
				 return MeritHolders[i].getCheckingAccounts()[k];
			 }else if(MeritHolders[i].getSavingsAccounts()[k].accountNumber == accountId) {
				 return MeritHolders[i].getSavingsAccounts()[k];
			 }
		 }
		 return null;
	 }
	 public static boolean readFromFile(String fileName) {
		 ArrayList<String> Lines = new ArrayList<String>();
		 String accountData;
		 try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			 while((accountData = br.readLine()) != null) {
				 Lines.add(accountData);
			 }
		 }catch(Exception e){
			 System.out.println(e);
		 }
		 
		 Iterator<String> iter = Lines.iterator();
		 int count = 0;
		 while(iter.hasNext()) {
			 switch(count) {
			 case 0: nextAccountNum = (Integer.parseInt(iter.next()));
			 		count++;
			 case 1: numOfferings = Integer.parseInt(iter.next());
			 		count++;
			 case 2: CDOffering[] tempOff = new CDOffering[numOfferings];
				 		for(int i = 0; i < numOfferings; i++) {
				 			if(iter.hasNext()) {
				 				tempOff[i] = CDOffering.readFromString(iter.next());
				 				
				 			}
				 			MeritCD = tempOff;
				 		}
				 	count++;
			 case 3: numAccountHolders = Integer.parseInt(iter.next());
			 		count++;
			 case 4: AccountHolder[] tempAccs = new AccountHolder[numAccountHolders];
			 			for(int i = 0; i < numAccountHolders; i++) {
			 				if(iter.hasNext()) {
			 					
									try {
										tempAccs[i] = AccountHolder.readFromString(iter.next());
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								
			 					if(iter.hasNext()) {
			 						tempAccs[i].setNumberOfCheckingAccounts( Integer.parseInt(iter.next()));
			 					}
			 					if(iter.hasNext() && tempAccs[i].getNumberOfCheckingAccounts() > 0) {
			 						for(int j = 0; j < tempAccs[i].getNumberOfCheckingAccounts(); j++) {
			 							try {
			 								
											tempAccs[i].addCheckingAccount(CheckingAccount.readFromString(iter.next()));
											
										
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											return false;
										} catch (java.lang.NumberFormatException e) {
											
											System.out.println(e);
											return false;
										} catch (ExceedsCombinedBalanceLimitException e) {
											// TODO Auto-generated catch block
											System.out.println(e);
										}
			 						}
			 					
			 						
			 					}
			 					
			 					for(int j = 0; j < Integer.valueOf(iter.next()); j++) {
			 						
			 					
			 					}
			 					
			 					
			 					if(iter.hasNext()) {
			 						tempAccs[i].setNumberofSavingsAccounts(Integer.parseInt(iter.next()));
			 					}
			 					if(iter.hasNext() && tempAccs[i].getNumberOfSavingsAccounts() > 0) {
			 						for(int j = 0; j < tempAccs[i].getNumberOfSavingsAccounts(); j++) {
			 							try {
			 								tempAccs[i].addSavingsAccount(SavingsAccount.readFromString(iter.next()));
			 								
			 							} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											return false;
										} catch (java.lang.NumberFormatException e) {
											
											System.out.println(e);
											return false;
										} catch (ExceedsCombinedBalanceLimitException e) {
											// TODO Auto-generated catch block
											System.out.println(e);
										}
			 						}
			 					}
			 					
			 					if(iter.hasNext()) {
			 						tempAccs[i].setNumberofCDAccounts(Integer.parseInt(iter.next()));
			 					}
			 					if(iter.hasNext() && tempAccs[i].getNumberOfCDAccounts() > 0) {
			 						for(int j = 0; j < tempAccs[i].getNumberOfCDAccounts(); j++) {
			 							try {
			 								if(iter.hasNext()) {
			 									tempAccs[i].addCDAccount(CDAccount.readFromString(iter.next()));
			 								}
			 								
			 							} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											return false;
										} catch (java.lang.NumberFormatException e) {
											
											System.out.println(e);
											return false;
										}
			 						}
			 					}
			 					
			 					
			 					
			 				}
			 			}
			 			for(int k = 0; k < tempAccs.length; k++) {
								System.out.println("tempSav " + tempAccs[k].getSavingsBalance());
							}
			 
			 			for(int i = 0; i < tempAccs.length; i++) {
			 				MeritHolders = tempAccs.clone();
			 			}
			 			
			 }
		 }
		 
		
		 return true;
	 }
	 
	 static AccountHolder[] sortAccountHolders() {
		 ArrayList<AccountHolder> holderAccount = new ArrayList<AccountHolder>();
		 
		 for(int i = 0; i < MeritHolders.length; i++) {
			 holderAccount.add(MeritHolders[i]);
		 }
		 
		 Collections.sort(holderAccount);
		 
		 AccountHolder[] sortedHolder = new AccountHolder[holderAccount.size()];
		 
		 for(int i = 0; i < holderAccount.size();i++) {
			 sortedHolder[i] = holderAccount.get(i);
		 }
		 
		 System.out.println(sortedHolder[0].getCheckingBalance());
		 System.out.println(sortedHolder[0].getSavingsBalance());
		 System.out.println(sortedHolder[0].getCDBalance());

		 
		 return sortedHolder;
	 }

	 static void setNextAccountNumber(long nextAccountNumber) {
		 nextAccountNum = (int)nextAccountNumber;
	 }
	


	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}