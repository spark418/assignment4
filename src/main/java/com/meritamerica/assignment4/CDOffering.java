package com.meritamerica.assignment4;

import java.util.ArrayList;

public class CDOffering {

	private  static int term ;
	private  static double interestRate;
	
	
	CDOffering(){
		
	}
	
	CDOffering(int t, double interest){
		term = t;
		interestRate = interest;
	}
	
	public int getTerm() {
		return term;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public static CDOffering readFromString(String cdOfferingDataString) {
	
  		CDOffering offering = new CDOffering();
  		String[] values = cdOfferingDataString.split(",");
		try {
			
			term = Integer.parseInt(values[0]);
			interestRate = Double.parseDouble(values[1]);
			
			
		}catch (NumberFormatException e) {
			throw e;
		}
		
		offering = new CDOffering(term, interestRate);
		
		return offering;
	}
	
	public String writeToString() {
		String offering = getTerm() + "," + getInterestRate()+"\n";
		return offering;
	}
}