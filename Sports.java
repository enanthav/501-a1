/* Name: Elizabeth Nanthavong 00326374
   Version: 2.3
   Features: This is a child of the parent class car that has a different appearance ('P') with a standard fuel consumption rate of 2 units but the ability to move 3 distance units.
   To keep the car lighter, the fuel tank capacity is only 30 units. If there is a heatwave, a sports car will overheat and consume fuel at double the normal rate (move distance is unchanged).
   The car will remain overheated only so long as the heatwave lasts.
 */
import java.io.*;
import javax.sound.sampled.*;

public class Sports extends Car{
    private int location;
    // SHADOWING CAR CLASS' CONSTANTS
    public static final int STARTING_FUEL = 30;
    public static final int STANDARD_DISTANCE = 3;
    public final int HEATWAVE_FUEL_CONSUMPTION = 4;

    // This constructor will take on a character as the parameter and change the appearance of the vehicle.
    public Sports(char appearance){
	    setAppearance(appearance);
	    setFuel(STARTING_FUEL);
    }

    // Adjusts fuel according to game state
    public int move(boolean heatwaveStatus){
    		if (heatwaveStatus) {
    			consumeFuel(HEATWAVE_FUEL_CONSUMPTION);
    		}
        else{ // else consume the fuel normally
            consumeFuel(CONSUMPTION_RATE);
        }
        printFuelConsumption(heatwaveStatus);
        makeSound();
	    return(STANDARD_DISTANCE);
    }
    
    public void printFuelConsumption(boolean heatwave) {
        System.out.println("Current fuel: " + getFuel());
		if (heatwave) {
			System.out.println("Fuel use: " + HEATWAVE_FUEL_CONSUMPTION);
		} else {
	        System.out.println("Fuel use: " + CONSUMPTION_RATE);
		}
    }
    
    public void printLocation() {
    		System.out.println("<<< Sports.trackLocation >>>");
        System.out.println("<<< New sports car location has been set to >>> " + location);
    }
    
    // Allows user to change location via cheat menu
    public void setLocation(int newDistance){
	if ((newDistance >=0) && (newDistance <= Track.SIZE -1)){ // ensure new location is valid
	    location = newDistance;
		}
    }
    // Ensures valid input when user sets the fuel
    public void changeFuel(int newFuel){
		System.out.println("sports.changeFuel");
		setFuel(newFuel);
    }

    // Returns current location of vehicle on array
    public int getLocation(){
	    return(location);
    }
}
