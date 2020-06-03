import java.lang.Math;

/**
 * This class is used to create a BatteryMeter.
 * A BatteryMeter is connected to an instance of a Battery class which has the ability to store a utility.
 * It is capable of recording and reporting the amount of power drawn from the battery and the total amount drawn from the mains.
 * @author Davide Gamba
 */

public class BatteryMeter extends Meter {

    Battery battery;

    /**
     * Default Constructor for a BatteryMeter object.
     * @param utilityName the name of the utility (usually electricity)
     * @param unitCost the cost per unit for the utility
     * @param meterReading the starting meterReading
     * @param battery the battery object associated with the BatteryMeter
     */
    public BatteryMeter(String utilityName, double unitCost, float meterReading, Battery battery){

        super(utilityName, unitCost, meterReading);
        this.battery = battery;
    }

    /**
     * Consumes the units depending on how many units are already stored inside the battery.
     * If the value of the units to be consumed are negative, the corresponding
     * positive value is stored inside the battery.
     * If they are positive, the energy needed to consume those units is taken from the battery's reservoir
     * @param units the units to be consumed by the Appliance
     */
    @Override
    public void consumeUnits(double units) {

        if(units < 0)
            storeUnits(units, battery);
        else if (battery.getEnergy() > 0 && units >= 1){
            battery.drawEnergy(Math.abs(units));
            if(battery.getEnergy() < 0) {
                meterReading += (Math.abs(battery.getEnergy()));
                battery.setEnergy(0);
            }
        }
        else
            meterReading += units;
    }

    /**
     * Stores the units in the battery
     * @param units the units to be stored
     * @param battery the battery in which to store the units
     */
    public void storeUnits(double units, Battery battery){

        battery.storeUnits(Math.abs(units));
    }

    /**
     * Writes a report similar to the one in Meter, in which, however,
     * the values of the battery, such as energy used and remaining energy are shown.
     */
    @Override
    public void report() {

        System.out.println("==Utility: " + utilityName + "==");
        if(meterReading < 0)
            System.out.println("Reading: 0");
        else
            System.out.println("Reading: " + meterReading);

        totalCost = unitCost * meterReading;
        System.out.println("Cost: " + Math.round(totalCost * 1000d)/1000d);
        System.out.println("==Batteries Status==");
        System.out.println("Energy Used: " + battery.getUnitsDrawn());
        System.out.println("Remaining Energy: " + battery.getEnergy());
        battery.setUnitsDrawn(0);
        System.out.println();
        calculateCredit();
    }

    /**
     * Calculates how much credit the owner of the house has accumulated towards the utility company,
     * based on the units that are stored inside the battery
     */
    public void calculateCredit(){
        if(battery.getEnergy() > 0)
            credit = battery.getEnergy() * unitCost;
    }
}
