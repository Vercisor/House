/**
 * This class represents an abstraction of an Appliance
 * An appliance has a name and a meter associated to it.
 * It has an abstract function (timePasses) to calculate the increment of one unit of time (one hour)
 * @author Davide Gamba
 */
public abstract class Appliance {
	
	private String name;
	private Meter meter;

	/**
	 * Constructor for the Appliance class
	 * Creates a simple Appliance with a name
	 * @param applianceName the name of the Appliance
	 */
	public Appliance(String applianceName){
		
		name = applianceName;
	}

	/**
	 * Connects the the specified meter to the Appliance
	 * @param applianceMeter
	 */
	public void setMeter(Meter applianceMeter){
		
		meter = applianceMeter;
	}

	/**
	 * denotes one increment of time passing (1 increment of time = 1 hour).
	 * Each concrete class that inherits from the abstract Appliance class will have to provide code
	 * that determines how it consumes or produces a utility when timePasses is called.
	 */
	public abstract void timePasses();

	/**
	 * Tells the meter to consume the units that have been used by the Appliance
	 * @param units the units to be consumes
	 */
	protected void tellMeterToConsumeUnits(double units) {

			meter.consumeUnits(units);
	}

}
