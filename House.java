import java.util.ArrayList;

/**
 * This class represents a House in which there are several Appliances.
 * The Appliances can be added and removed through the methods this class.
 * It runs the simulation and includes the main method
 * @author Davide Gamba
 */
public class House {

    private Meter electricity;
    private Meter water;
    private ArrayList<Appliance> appliances = new ArrayList<>();

    /**
     * House constructor with no parameters
     * Creates a House with two default meters, one for electricity and one for water
     */
    public House(){
        electricity = new Meter("Electricity", 0.013, 0);
        water = new Meter("Water", 0.002, 0);
    }

    /**
     * Second Constructor for the House, thanks to which it is possible
     * to specify the meters for the House
     * @param firstMeter first meter, can also be a Battery
     * @param secondMeter second meter available, can be Water
     */
    public House(Meter firstMeter, Meter secondMeter){
        electricity = firstMeter;
        water = secondMeter;
    }

    /**
     * Adds the specified Appliance to the House, and sets its meter to be the House electric meter.
     * @param appliance the appliance to be added
     */
    public void addElectricAppliance(Appliance appliance){
        appliances.add(appliance);
        appliances.get(appliances.size() -1).setMeter(electricity);
    }

    /**
     * Adds the specified Appliance to the House, and sets its meter to be the House water meter.
     * @param appliance the appliance to be added
     */
    public void addWaterAppliance(Appliance appliance){
        appliances.add(appliance);
        appliances.get(appliances.size() -1).setMeter(water);
    }

    /**
     * Removes the specified Appliance from the House.
     * @param appliance the appliance to be removed
     */
    public void removeAppliance(Appliance appliance){
        appliances.remove(appliance);
    }

    /**
     * Returns the number of Appliances in the House
     * @return the size of the appliances ArrayList
     */
    public int numAppliances(){
        return appliances.size();
    }

    /**
     * Simulates one unit of time (an hour) passing in the house,
     * by calling timePasses() on each of the appliances in the house once
     */
    public void activate(){
        for(Appliance a : appliances){
            a.timePasses();
        }
        electricity.report();
        water.report();
    }

    /**
     * Runs the simulation for as many days and as many hours as the users specified.
     * Days and Hours can be combined (such as 2 days and 3 hours)
     * @param days the number of days the simulation has to run for
     * @param hours the number of hours the simulation has to run for
     */
    public void activate(int days, int hours){

        int temp = hours;
        if(hours > 24 || (hours <= 0 && days < 1))
            throw new IllegalArgumentException("The number of active hours must be between 1 and 24");


        // First loop that runs for as many days as specified by the argument "days"
        for(int x = 0; x <= days; x++) {

            System.out.println("########## DAY " + (x+1) + " ########## \n");

            try{Thread.sleep(500);}
            catch(InterruptedException e){}

            if(x >= days) {
                hours = temp;
            }
            else
                hours = 24;

            // Second loop that runs for as many hours as specified by the argument "hours"
            // and prints the report at the end of every hour
            for (int y = 0; y < hours; y++) {

                for (Appliance a : appliances) {
                    a.timePasses();
                }

                System.out.println("HOUR: " + (y+1));

                electricity.report();
                water.report();
                System.out.println("//////////////////\n");

                //Makes the program sleep for one second between every iterations of the loop
                try{Thread.sleep(1000);}
                catch(InterruptedException e){}
            }

            // For all appliances that operate on a Cycle, it resets their active hours
            // to the original value at the end of each day
            for(Appliance a : appliances){
                if (a instanceof CyclicFixed)
                    ((CyclicFixed) a).resetActiveHours();
                else if (a instanceof CyclicVaries)
                    ((CyclicVaries) a).resetActiveHours();

            }
        }

        endOfSimulation(days, hours);
    }

    /**
     * Prints the final report of the simulation. This includes total costs
     * and the credit in electricity accumulated by the battery
     * @param days the number of days for which the simulation ran
     * @param hours the number of hours for which the simulation ran
     */
    public void endOfSimulation(int days, int hours){

        double simulationTotal;

        System.out.println("####### END OF SIMULATION REACHED #######");
        System.out.println("The simulation ran for: " + days + " Days and " + hours + " Hours");
        System.out.println("===================");
        System.out.println("ELECTRICITY METER READING: " + electricity.getMeterReading());
        System.out.println("ELECTRICITY COST: £" + Math.round(electricity.getTotalCost() * 10000d)/10000d);

        if(electricity.getCredit() > 0)
            System.out.println("ELECTRICITY CREDIT: £" + Math.round(electricity.getCredit() * 10000d)/10000d);

        System.out.println("===================");
        System.out.println("WATER METER READING: " + water.getMeterReading());
        System.out.println("WATER COST: £" + Math.round(water.getTotalCost() * 10000d)/10000d);
        System.out.println("===================");

        //Calculates how much credit the owner of the house has accumulated and prints a different message according to their balance
        if(electricity.getCredit() > electricity.getTotalCost()) {
            simulationTotal = (electricity.getTotalCost() + water.getTotalCost()) - electricity.getCredit();
            simulationTotal = Math.round(simulationTotal * 1000d)/1000d;

            if (simulationTotal < 0)
                System.out.println("THE UTILITY COMPANIES OWE YOU: £" + Math.abs(simulationTotal));
            else {
                System.out.println("TOTAL COST FOR THIS MONTH: £" + simulationTotal);
                System.out.println("Remember to pay your bills on time!");
            }
        }

        else {
            System.out.println("TOTAL COST IN £: " + (Math.round((electricity.getTotalCost() + water.getTotalCost())*10000d)/10000d));
            System.out.println("Remember to pay your bills on time!");
        }
    }

    /**
     * Main method from which the simulation is executed, it takes three parameters
     * @param args Textfile.txt, number of days, number of hours
     */
    public static void main(String args[]){

        House house;
        SimulationReader simulationReader = new SimulationReader(args[0]);
        //SimulationReader simulationReader = new SimulationReader("src/myHouse.txt");
        house = simulationReader.getAppliances();

        //house.activate(2, 0);


        house.activate(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }

}
