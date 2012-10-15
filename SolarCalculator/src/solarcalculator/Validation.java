package solarcalculator;

public class Validation {
	
	public Validation() {
		
	}
	
	public boolean checkFloatRange(float input, float minimum, float maximum) {
		boolean valid;
		
		if ((input < minimum) || (input > maximum)) {
			valid = false;
		} else {
			valid = true;
		}
		
		return valid;
		
	}
	
	public boolean checkIntRange(int input, int minimum, int maximum) {
		boolean valid;
		
		if ((input < minimum) || (input > maximum)) {
			valid = false;
		} else {
			valid = true;
		}
		
		return valid;
		
	}

}
