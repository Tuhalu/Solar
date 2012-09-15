package solarcalculator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.*;
import com.google.appengine.api.datastore.Key;
import java.util.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class System {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	// Address info?
	@Persistent
	private int yearsSinceInstall;
	
	@Persistent
	private int monthsSinceInstall;
	
	@Persistent
	private float dailyUsage;
	
	@Persistent
	private float dayTimeHourlyUsage;
	
	@Persistent
	private boolean isExisting;
	
	@Persistent
	private float longitude;

	@Persistent
	private float latitude;
	
	@Persistent
	private int panelFacing;
	
	@Persistent
	private int roofAngle;
	
	@Persistent
	private float panelOutput;
	
	@Persistent
	private int inverterCost;
	
	@Persistent 
	private int panelCost;
	
	@Persistent 
	private float panelEfficiencyLossPerYear;
	
	@Persistent 
	private float inverterEfficiencyLossPerYear;
	
	@Persistent
	private int panelLifespan;
	
	@Persistent
	private int inverterLifespan;
	
	
	public System() {
		
	}
	
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getPanelFacing() {
		return panelFacing;
	}

	public void setPanelFacing(int panelFacing) {
		this.panelFacing = panelFacing;
	}

	public int getRoofAngle() {
		return roofAngle;
	}

	public void setRoofAngle(int roofAngle) {
		this.roofAngle = roofAngle;
	}

	public float getPanelOutput() {
		return panelOutput;
	}

	public void setPanelOutput(float panelOutput) {
		this.panelOutput = panelOutput;
	}

	public int getInverterCost() {
		return inverterCost;
	}

	public void setInverterCost(int inverterCost) {
		this.inverterCost = inverterCost;
	}

	public int getPanelCost() {
		return panelCost;
	}

	public void setPanelCost(int panelCost) {
		this.panelCost = panelCost;
	}

	public float getPanelEfficiencyLossPerYear() {
		return panelEfficiencyLossPerYear;
	}

	public void setPanelEfficiencyLossPerYear(float panelEfficiencyLossPerYear) {
		this.panelEfficiencyLossPerYear = panelEfficiencyLossPerYear;
	}

	public float getInverterEfficiencyLossPerYear() {
		return inverterEfficiencyLossPerYear;
	}

	public void setInverterEfficiencyLossPerYear(float inverterEfficiencyLossPerYear) {
		this.inverterEfficiencyLossPerYear = inverterEfficiencyLossPerYear;
	}

	public int getPanelLifespan() {
		return panelLifespan;
	}

	public void setPanelLifespan(int panelLifespan) {
		this.panelLifespan = panelLifespan;
	}

	public int getInverterLifespan() {
		return inverterLifespan;
	}

	public void setInverterLifespan(int inverterLifespan) {
		this.inverterLifespan = inverterLifespan;
	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public boolean isExisting() {
		return isExisting;
	}

	public void setExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}
	
}