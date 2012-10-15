package solarcalculator;

import java.math.*;

// DIS WERE DA MATH @
public class Calculator {
	
	private transient System system;
	
	private static final int bracingCost = 400;
	private static final float initialPanelEfficiency = 1f;
	private static final float tarriff11Fee = 0.25378f;
	private static final float feedInFee = 0.5f;
	private static final float annualTarriffIncrease = 0.05f;
	
	
	Calculator(System system) {
		this.system = system; //MFW		
	}
	
	/*
	 * Returns the total cost for the system
	 */
	public int systemCost() {
		int totalCost;
		
		totalCost = system.getPanelCost() + system.getInverterCost();
		
		if (system.isBraced()) {
			totalCost += bracingCost;
		}
		
		return totalCost;
	}
	
	public float output(int year, int days) {
		float dailyOutput;
		
		dailyOutput = system.getPanelOutput()
				* panelEfficiencyForYear(year)
				* inverterEfficiencyForYear(year)
				* system.getAverageSunlight()
				* days;
		
		return dailyOutput;
	}
	
	public float panelEfficiencyForYear(int year) {
		float efficiency;
		
		efficiency = initialPanelEfficiency 
				- (system.getPanelEfficiencyLossPerYear() * year);
		
		return efficiency;
	}
	
	public float inverterEfficiencyForYear(int year) {
		float efficiency;
		
		efficiency = system.getInverterEfficiency() 
				- (system.getInverterEfficiencyLossPerYear() * year);
		
		return efficiency;
	}
	
	public float tarriff11ForYear(int year) {
		float tarriffIncrease;
		float currentTarriffFee;
		
		tarriffIncrease = year * annualTarriffIncrease;
		
		currentTarriffFee = tarriff11Fee
				+ (tarriff11Fee * tarriffIncrease);
		
		return currentTarriffFee;
	}
	
	public float savings(int year, int days) {
		float monthlyReplacementGeneration;
		float monthlyReplacementSavings;
		float monthlyExportedIncome;
		float monthlySavings;
		
		monthlyReplacementGeneration = system.getAverageSunlight()
				* system.getDayTimeHourlyUsage()
				* days;
		
		monthlyReplacementSavings = monthlyReplacementGeneration
				* tarriff11ForYear(year);
		
		monthlyExportedIncome = (output(year, days)
				- monthlyReplacementGeneration)
				* feedInFee;
		
		monthlySavings = monthlyReplacementSavings + monthlyExportedIncome;
		return monthlySavings;
	}
	
	
	
	public int returnOnInvestment() {
		float accumulatedSavings = 0f;
		int year = 0;
		
		do {
			accumulatedSavings += savings(year, 365);
			year += 1;
			
		} while (accumulatedSavings < systemCost());
		
		return year;
	}

}