/* Name: Elizabeth Nanthavong 00326374
   Version: 2.5
   Specific features: A child of class Car that has the appearance ('V') and different movement options: under normal driving mode the distance travelled is still the default rate of 2 but the fuel consumption is 3 units. If the    
   car is under AWD mode the car can still move when there is a blizzard, but at a reduced speed. The fuel tank is larger with a capacity of 50 units.
   Limitations: 
*/
import java.io.*;
import javax.sound.sampled.*;

public class SUV extends Car{
    private int location = 0; // SUV starts off at index 0 on array
    private boolean normalMode = true; // Default set to normal mode on when starting game
    // Shadow constants in car since the values for starting fuel and consumption rate differ
    public static final int STARTING_FUEL = 50;
    public static final int CONSUMPTION_RATE = 3;
    public final int AWD_DISTANCE = 1;

    public SUV(char appearance){
	    setAppearance(appearance);
        setFuel(STARTING_FUEL);
    }
    
    // Process the driving mode selected by the user
    public boolean drivingMode(int choice){
        if (Debug.getOn() == true){
            System.out.println("<<< drivingMode() >>>");
        }
        switch(choice) {
            case 65: // check if choice made was equal to 'A' (AWD)
                normalMode = false;
                System.out.println("The SUV is in AWD mode");
                break;

            case 96:  // check if choice made was equal to 'a' (AWD)
                normalMode = false;
                System.out.println("The SUV is in AWD mode");
                break;

            case 68: // check if choice made was equal to 'D' (normal)
                normalMode = true;
                System.out.println("The SUV is driving in normal mode");
                break;
            case 100: // check if choice made was equal to 'd' (normal)
                normalMode = true;
                System.out.println("The SUV is driving in normal mode");
            default:
                System.out.println("Invalid input");
        }
        return(normalMode);
    }
    // Consumes fuel when driving in normal mode
    public int move()
    {
	   consumeFuel(this.CONSUMPTION_RATE);
	   System.out.println("Current fuel: " + this.getFuel());
	   System.out.println("Fuel use: " + this.CONSUMPTION_RATE);
       makeSound();
	   return(STANDARD_DISTANCE);
    }

    // Consumes fuel when driving in AWD and returns distance moved in AWD
    public int moveAWD(){
	   consumeFuel(this.CONSUMPTION_RATE);
	   System.out.println("Current fuel: " + this.getFuel());
	   System.out.println("Fuel use: " + this.CONSUMPTION_RATE);
	   return(AWD_DISTANCE);
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
