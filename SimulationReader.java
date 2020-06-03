import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Allows the user to load a simulation form a file in the ".txt" format
 */
public class SimulationReader {
    private BufferedReader reader;
    private String fileName;

    /**
     * Creates a SimulatorReader object that reads a file
     * @param fileName the name of the file that needs to be read
     */
    public SimulationReader(String fileName){
        this.fileName = fileName;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    /**
     * Reads the next line
     * @return the line read as a String
     */
    public String getLine(){

        String line = "";

        try {
            line = reader.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return line;
    }

    /**
     * Checks if the file exists and whether it is ready to be read
     * @return false: if the file is not ready
     *         true: if the file is readable
     */
    public boolean fileIsReady(){

        boolean ready = false;

        try{
            ready = reader.ready();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ready;
    }

    /**
     * It assigns the appliances from the .txt file to a House object.
     * It checks which meter has to be assigned to each Appliance and sorts them
     * by type (CyclicFixed, CyclicVaries etc...)
     * @return a House object containing all of the appliances in the .txt file
     */
    public House getAppliances() {


        Battery battery = new Battery("Battery", 30);
        Meter electricity = new BatteryMeter("Electricity", 0.013, 0, battery);
        Meter water = new Meter("Water", 0.002, 0);
        House house = new House(electricity, water);
        String name;
        String minUnits = "";
        String maxUnits = "";
        String fixUnits = "";
        String probability = "";
        String cycle = "";

        // Reads from the file, as long as there are lines to be read
        // Splits each line in order to get the relevant data for each variable
        while (fileIsReady()) {

            String line = this.getLine();
            try{name = line.split(": ")[1];}
            catch(Exception e){ break; }

            line = this.getLine();
            String subclass = line.split(": ")[1];

            line = this.getLine();
            String meter = line.split(": ")[1];

            line = this.getLine();
            try{minUnits = line.split(": ")[1];}
            catch(Exception e){}

            line = this.getLine();
            try{maxUnits = line.split(": ")[1];}
            catch(Exception e){}

            line = this.getLine();
            try{fixUnits = line.split(": ")[1];}
            catch(Exception e){}

            line = this.getLine();
            try{probability = line.split(": ")[1];
            probability = probability.split(" in ")[1];}
            catch(Exception e){}

            line = this.getLine();
            try{cycle = line.split(": ")[1];
            cycle = cycle.split("/")[0];}
            catch(Exception e){}


            line = this.getLine();


            // Creates and adds the Appliances to the House
            switch (subclass) {
                case "CyclicFixed":
                    if (meter.compareTo("electric") == 0)
                        house.addElectricAppliance(new CyclicFixed(name, Double.parseDouble(fixUnits), Integer.parseInt(cycle)));
                    else
                        house.addWaterAppliance(new CyclicFixed(name, Integer.parseInt(fixUnits), Integer.parseInt(cycle)));
                    break;
                case "CyclicVaries":
                    if (meter.compareTo("electric") == 0)
                        house.addElectricAppliance(new CyclicVaries(name, Integer.parseInt(minUnits), Integer.parseInt(maxUnits), Integer.parseInt(cycle)));
                    else
                        house.addWaterAppliance(new CyclicVaries(name, Integer.parseInt(minUnits), Integer.parseInt(maxUnits), Integer.parseInt(cycle)));
                    break;
                case "RandomFixed":
                    if (meter.compareTo("electric") == 0)
                        house.addElectricAppliance(new RandomFixed(name, Integer.parseInt(probability), Double.parseDouble(fixUnits)));
                    else
                        house.addWaterAppliance(new RandomFixed(name, Integer.parseInt(probability),  Double.parseDouble(fixUnits)));
                    break;

                case "RandomVaries":
                    if (meter.compareTo("electric") == 0)
                        house.addElectricAppliance(new RandomVaries(name, Integer.parseInt(minUnits), Integer.parseInt(maxUnits), Integer.parseInt(probability)));
                    else
                        house.addWaterAppliance(new RandomVaries(name, Integer.parseInt(minUnits), Integer.parseInt(maxUnits), Integer.parseInt(probability)));
                    break;
            }
        }
        return house;
    }
}
