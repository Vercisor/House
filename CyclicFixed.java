
/**
 * This class is used to create an Appliance with a fixed Cycle.
 * The appliance will run for a set number of hours each day, consuming a fixed amount of units per hour
 * @author Davide Gamba
 */

public class CyclicFixed extends Appliance {

	private double unitsConsumption;
	private int activeHours;
	private int passingHours;

	/**
	 * Default Constructor for a CyclicFixed Appliance
	 * @param name the name of the appliance
	 * @param unitsConsumption the amount of units that is consumed every hour by the Appliance
	 * @param hours the number of hours in which the Appliance is active for each day.
	 *              "hours"  must be an integer between 1 and 24 inclusive
	 */
	public CyclicFixed(String name, double unitsConsumption, int hours) {
		super(name);
		this.unitsConsumption = unitsConsumption;
		if(hours < 1 || hours > 24)
			throw new IllegalArgumentException("The number of active hours must be between 1 and 24");
		else
			activeHours = hours;
			passingHours = activeHours;
	}

	/**
	 * Denotes one increment of time.
	 * Checks if the Appliance still has hours in its cycle.
	 * It tells the meter to consume the units that the appliance
	 * consumes every hour and decreases by 1 the active hours
	 */
	@Override
	public void timePasses() {

		if(passingHours != 0) {
			super.tellMeterToConsumeUnits(unitsConsumption);
			passingHours--;
		}

	}

	/**
	 * Sets the active hours to their original value
	 */
	public void resetActiveHours() {
		passingHours = activeHours;
	}
}
