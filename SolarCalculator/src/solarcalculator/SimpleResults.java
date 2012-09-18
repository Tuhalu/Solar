package solarcalculator;

public class SimpleResults {
	
	private System system;
	
	private float ROI;
	private float dailyOutput;
	private float systemCost;
	private int monthOneSavings;
	private int lifespanSavings;
	
	private static final int tarriff11Cost = 1;
	
	public SimpleResults(System system) {
		this.system = system;
		calculate();
	}
	
	
	
	private void calculate() {
		
		
		
		for (int year = 1; year <= system.getPanelLifespan(); year++) {
			
			float dailyOutput = system.getPanelOutput() * (1 - (system.getPanelEfficiencyLossPerYear() * 
					year)) * (1 - (system.getInverterEfficiencyLossPerYear() * year));
			
		}
	}
	
	
	

}
