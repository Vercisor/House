import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to create an Appliance with a cyclic amount of daily operative
 * hours and with a variable consumption of unit for each active hour.
 * The appliance will run for a set number of hours each day, consuming a variable amount of units per hour,
 * defined in a range of minimum and maximum unit consumption
 * @author Davide Gamba
 */
public class CyclicVaries extends Appliance {
    private int unitsConsumed = 0;
    private int minUnits;
    private int maxUnits;
    private int passingHours;
    private int activeHours;

    /**
     * Default Constructor for a CyclicVaries Appliance
     * @param name the name of the Appliance
     * @param minUnits the minimum amount of units tha the the appliance can consume in one hour
     * @param maxUnits the maximum amount of units tha the the appliance can consume in one hour
     * @param hours  the number of hours in which the Appliance is active for each day.
     * 	             "hours"  must be an integer between 1 and 24 inclusive
     */
    public CyclicVaries(String name, int minUnits, int maxUnits, int hours) {
        super(name);
        this.minUnits = minUnits;
        this.maxUnits = maxUnits;
        if(hours < 1 || hours > 24)
            throw new IllegalArgumentException("The number of active hours must be between 1 and 24");
        else {
            activeHours = hours;
            passingHours = activeHours;
        }
    }

    /**
     * Denotes one increment of time.
     * Checks if the Appliance still has hours in its cycle.
     * It calculates a random amount of units to be consumed using minUnits
     * and maxUnits as boundaries and tells the meter to consume those calculated units
     * it works with both positive and negative units.
     * Decreases the active hours every time that the time passes.
     */
    @Override
    public void timePasses() {

        if(passingHours != 0) {
            if(minUnits < 0)
                super.tellMeterToConsumeUnits(ThreadLocalRandom.current().nextInt(maxUnits, minUnits + 1));
            else
                super.tellMeterToConsumeUnits(ThreadLocalRandom.current().nextInt(minUnits, maxUnits + 1));
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
