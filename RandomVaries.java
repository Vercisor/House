import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to create an Appliance with a random amount of daily operative
 * hours and with a variable consumption of unit for each active hour.
 * The appliance will run for a random number of hours each day, consuming a variable amount of units per hour,
 * defined in a range of minimum and maximum unit consumption
 * @author Davide Gamba
 */
public class RandomVaries extends Appliance {

    private int minUnits;
    private int maxUnits;
    private int probability;

    /**
     * Default Constructor for a CyclicVaries Appliance
     * @param name the name of the Appliance
     * @param minUnits the minimum amount of units tha the the appliance can consume in one hour
     * @param maxUnits the maximum amount of units tha the the appliance can consume in one hour
     * @param probability the probability of it being turned on during an hour
     */
    public RandomVaries(String name, int minUnits, int maxUnits, int probability) {
        super(name);
        this.minUnits = minUnits;
        this.maxUnits = maxUnits;
        this.probability = probability;
    }

    /**
     * Represents one increment of time.
     * It determines if, based on the probability, the Appliance should be turned on.
     * It calculates a random amount of units to be consumed using minUnits
     * and maxUnits as boundaries and tells the meter to consume those calculated units
     * it works with both positive and negative units.
     */
    @Override
    public void timePasses() {

        if((ThreadLocalRandom.current().nextInt(1, probability + 1)) == 1) {
            if(minUnits < 0)
                super.tellMeterToConsumeUnits(ThreadLocalRandom.current().nextDouble(maxUnits, minUnits + 1));
            else
                super.tellMeterToConsumeUnits(ThreadLocalRandom.current().nextDouble(minUnits, maxUnits + 1));
        }
    }
}
