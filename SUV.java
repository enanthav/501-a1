/* Name: Elizabeth Nanthavong 00326374
   Version: 2.5
   Specific features: A child of class Car that has the appearance ('V') and different movement options: under normal driving mode the distance travelled is still the default rate of 2 but the fuel consumption is 3 units. If the    
   car is under AWD mode the car can still move when there is a blizzard, but at a reduced speed. The fuel tank is larger with a capacity of 50 units.
   Limitations: 
*/
import java.io.*;
import javax.sound.sampled.*;

public class SUV extends Car{
    private boolean normalMode = true; // Default set to normal mode on when starting game
    // Shadow constants in car since the values for starting fuel and consumption rate differ
    public static final int STARTING_FUEL = 50;
    public static final int CONSUMPTION_RATE = 3;
    public final int AWD_DISTANCE = 1;

    public SUV(char appearance){
	    setAppearance(appearance);
        setFuel(STARTING_FUEL);
        location = 0;
    }
    
    // Process the driving mode selected by the user
    public boolean drivingMode(int choice){
        if (Debug.getOn() == true){
            System.out.println("<<< drivingMode() >>>");
        }
     // check if choice made was equal to 'A' - 65  or 'a' - 96 (AWD) 
        if (choice == 65  || choice == 96) {
        		normalMode = false;
        		System.out.println("The SUV is in AWD mode");
        } else if (choice == 68 || choice == 100) {
        		normalMode = true;
        		System.out.println("The SUV is driving in normal mode");
        }
        return(normalMode);
    }
    // Consumes fuel when driving in normal mode
    public int move()
    {
	   consumeFuel(this.CONSUMPTION_RATE);
	   printFuelConsumption();
       makeSound();
	   return(STANDARD_DISTANCE);
    }

    // Consumes fuel when driving in AWD and returns distance moved in AWD
    public int moveAWD(){
	   consumeFuel(this.CONSUMPTION_RATE);
	   printFuelConsumption();
	   return(AWD_DISTANCE);
    }

    public void printFuelConsumption() {
 	   System.out.println("Current fuel: " + getFuel());
 	   System.out.println("Fuel use: " + CONSUMPTION_RATE);
    }
    // Returns whether normal mode is on (true) or off (false)
    public boolean getNormalMode(){
	    return(normalMode);
    }
    
    public void printLocation(){
    		System.out.println("<<< Tracking SUV location >>> " + location);
    }
    // Allows user to change the fuel value through the cheat menu
    public void changeFuel(int newFuel){
    		setFuel(newFuel);
    }

    // Allows user to change location via cheat menu
    public void setLocation(int newLocation){
	if (newLocation <= Track.SIZE-1){ // ensure the location is between 0 and 24 before changing it
	    location = newLocation;
		}
    }

    // Returns current location of vehicle on array
    public int getLocation(){
	    return(location);
    }
}
