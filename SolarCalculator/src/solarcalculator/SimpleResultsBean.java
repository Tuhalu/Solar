package solarcalculator;

import java.io.Serializable;

import javax.jdo.PersistenceManager;



public class SimpleResultsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2282655615339689636L;
	private String userID;
	private transient Calculator calculator;

	public SimpleResultsBean() {
		
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		
		this.userID = userID;
		setup();
	}
	
	private void setup() {
		long id = Long.parseLong(userID);
		PersistenceManager manager = PMF.get().getPersistenceManager();
		System system = manager.getObjectById(System.class, id);
		manager.close();
		calculator = new Calculator(system);
	}
	
	public String ROI() {
		String output;
		int year;
		
		year = calculator.returnOnInvestment();
		
		output = year + " years.";
		
		
		return output;
		
	}
	
	public String dailyOutput() {
		String output;
		int year = 0;
		float dailyOutput = calculator.output(year, 1);
		
		output = dailyOutput + "KwH";
		
		return output;		
	}
	
	public String cost() {
		String output;
		int cost;
		cost = calculator.systemCost();
		
		output = "$" + cost;
		
		return output;		
	}
	
	public String savingsFirstMonth() {
		String output;
		int year = 0;
		int timescale = 30;
		
		float savings;
		savings = calculator.savings(year, timescale);
		
		output = "$" + savings;
		
		return output;
	}
	
	public String savingsLifespan() {
		String output;
		int year = 0;
		int timescale = 365 * 20;
		
		float savings;
		savings = calculator.savings(year, timescale);
		
		output = "$" + savings;
		
		return output;
	}
}