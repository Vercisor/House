/**
 * This class is used to create a Battery object.
 * A Battery can store and give energy to an electrical system.
 * It has a BatteryMeter which manages the use of the battery
 * @author Davide Gamba
 */
public class Battery {

    private String batteryName;
    private int capacity;
    private int energy;
    private double unitsDrawn;

    /**
     * Default Constructor of the Battery, creates a Battery with a name and a capacity
     * @param batteryName the name of the battery
     * @param capacity the amount of energy that the battery can store
     */
    public Battery(String batteryName, int capacity) {
        this.batteryName = batteryName;
        this.capacity = capacity;
    }

    /**
     * Allows the battery to store units.
     * If the energy that has to be stored exceeds the capacity
     * of the battery, the excess energy is thrown away.
     * @param units the units that have to be stored
     */
    public void storeUnits(double units){
        if(energy < capacity){
            energy += units;
            if(energy > capacity)
                energy = capacity;
        }

    }

    /**
     * Allows the battery to give energy to the system to power the different Appliances
     * @param units the units that need to be withdrawn from the battery
     */
    public void drawEnergy(double units){
        double temp = energy;
        energy -= units;
        if(energy <= 0)
            unitsDrawn = temp;
        else
            unitsDrawn += temp - energy;
    }

    /**
     * @return the amount of units that were withdrawn from the battery during the current hour
     */
    public double getUnitsDrawn() {
        return unitsDrawn;
    }

    /**
     * @return the energy currently stored in the battery
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * @param units sets the value of the energy contained in the battery
     */
    public void setEnergy(int units){
        energy = 0;
    }

    /**
     * @param unitsDrawn sets the value of the instance variable
     *                   representing the units withdrawn from the battery
     */
    public void setUnitsDrawn(double unitsDrawn) {
        this.unitsDrawn = unitsDrawn;
    }
}
