import java.util.concurrent.ThreadLocalRandom;


/**
 * This class is used to create an Appliance with a random amount of daily operative
 * hours and with a fixed consumption of unit for each active hour.
 * The appliance will run for a random number of hours each day, consuming a fixed amount of units per hour
 * @author Davide Gamba
 */
public class RandomFixed extends Appliance {

    private double unitsConsumption;
    private int probability;

    /**
     * Default Constructor for a RandomFixed Appliance
     * @param name the name of the Appliance
     * @param probability the probability of it being turned on during an hour
     * @param unitsConsumption the amount of units that is consumed every hour by the Appliance
     */
    public RandomFixed(String name, int probability, double unitsConsumption) {
        super(name);
        this.unitsConsumption = unitsConsumption;
        this.probability = probability;
    }

    /**
     * Calculates if the appliance is on in this increment of time, if it is,
     * the method tells the meter to consume the fixed amount of units
     */
    @Override
    public void timePasses() {

        if((ThreadLocalRandom.current().nextInt(1, probability + 1)) == 1) {
            super.tellMeterToConsumeUnits(unitsConsumption);
        }

    }

}
