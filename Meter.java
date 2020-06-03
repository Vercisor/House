/**
 * a Meter represents an object that manages the consumption and production of a particular utility.
 * it has the ability to report the amount of units of the utility that have been used and the
 * corresponding cost incurred to the home occupier
 * @author Davide Gamba
 */

public class Meter {
	
	protected String utilityName;
	protected double unitCost;
	protected float meterReading;
	protected double totalCost = 0;
	protected double credit = 0;

	/**
	 * Default Constructor for a Meter object
	 * @param name the name of the meter
	 * @param cost the cost of one unit of this type of utility
	 * @param reading a variable representing the balance of units that have been used since
	 * the last meter reading
	 */
	public Meter(String name, double cost, float reading){
		
		utilityName = name;
		unitCost = cost;
		meterReading = reading;
	}

	/**
	 * Thells the meter to consume the units
	 * @param units the units to be consumed
	 */
	public void consumeUnits(double units){
		
		meterReading += units;
	}

	/**
	 * Generates a report for the utility that includes the name, the reading on the meter
	 * and the cost in which the owner of the house incurs
	 */
	public void report(){	
		
		System.out.println("==Utility: " + utilityName + "==");

		System.out.println("Meter Reading " + meterReading);

		totalCost = unitCost * meterReading;

		if(unitCost * meterReading < 0)
			System.out.println("Cost: 0");
		else
			System.out.println("Cost: " + totalCost);

	}

	/**
	 * @return the credit accumulated by the owner toward the utility company
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * @return the total cost for the utility to which the meter is attached
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @return the meter reading
	 */
	public float getMeterReading() {
		return meterReading;
	}

}
