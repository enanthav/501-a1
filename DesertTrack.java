/*
  Version: 3.0
  Specific Features: This is a child of the parent class Track with an additional ability to generate a heatwave. A sports car travelling during a heat wave will overheat. The effect of overheating is consuming four fuel points versus 2. Each turn there's a 10% chance of a heatwave occurring, otherwise the temperature is normal.
*/
public class DesertTrack extends Track{
    private Sports sportsCar = new Sports('S');
    private boolean heatwaveModeOn;

    // set the location of the sports car to 0 on the array
    public DesertTrack(){
        setLocation(sportsCar, 0);
    }
    // Returns a reference to sports car
    public Sports getSportsCar(){
	    return(sportsCar);
    }
    // Turns heatwave on
    public void setHeatwaveMode(){
	    if (Debug.getOn() == true){
	        System.out.println("Heatwave mode is on");
	    }
	    heatwaveModeOn = true;
    }
    // Turns heatwave off
    public void turnHeatwaveOff(){
	    if (Debug.getOn() == true){
	        System.out.println("Heatwave mode is off");
	        }
        heatwaveModeOn = false;
    }

    // Return heatwaveModeOn, heatwaveModeOn is true when it is on
    public boolean getHeatwaveMode(){
    	return(heatwaveModeOn);
    }

    // Display driving options
    public void drivingMenu(){
	    System.out.println("Sportscar Driving Options");
	    System.out.println("-------------------------");
	    System.out.println("(d)rive normally");
	    System.out.println("(q)uit simulation");
	    System.out.println("Enter selection: ");
    }


    // Move the car along the array
    public boolean moveCar(int selection){
        if (Debug.getOn() == true){
            System.out.println("<<< Desert.moveCar() >>>");
        }
        int D = 68; int d = 100; // ASCII values for 'D' and 'd'
	    boolean validInput = false; // will return true if the user input is valid
	    if ((selection == D) || (selection == d)){ // check for valid input
                validInput = true;
                if ((sportsCar.getFuel() >= Sports.CONSUMPTION_RATE) && (heatwaveModeOn == false)){ // check to ensure there is enough fuel for a move in normal weather
                    sportsCar.move(heatwaveModeOn); // processes the move while heatwave is off
                    updateGrid(Sports.STANDARD_DISTANCE); // updates grid with new location of car
                    System.out.println("Distance moved: " + Sports.STANDARD_DISTANCE + "\n");
                    }
                else if ((sportsCar.getFuel() >= sportsCar.HEATWAVE_FUEL_CONSUMPTION) && (heatwaveModeOn == true)){ // check to ensure that there is greater than or equal to 4 fuel when heatwave is on
                    sportsCar.move(heatwaveModeOn); // processes the move while heatwave is on
                    updateGrid(Sports.STANDARD_DISTANCE); // updates grid with new location of car
                    System.out.println("Distance moved: " + Sports.STANDARD_DISTANCE + "\n");
                }
                else{ // display no fuel message to user
                    System.out.println("There is not enough fuel to move!");
                }
            }
        return(validInput); // return true if the input was valid
    }

    // Update the grid with the new location of the car and wipe the old location
    public void updateGrid(int newDistance){
        int oldLocation = sportsCar.getLocation();
        sportsCar.trackLocation(newDistance); // track new location of the sports car
        getTrack()[sportsCar.getLocation()] = sportsCar; // update array with sports car object
        getTrack()[oldLocation] = null; // wipe old location

        if (Debug.getOn() == true){
            System.out.println("<<< sportsCar.updateGrid() >>>");
            System.out.println(oldLocation + " OLD LOCATION");
            System.out.println(sportsCar.getLocation() + " NEW LOCATION"); 
        }
    }
    // Processes user input and calls appropriate functions to set new location for car
    public void cheatmodeSetLocation(int selection){
        if (Debug.getOn() == true){
            System.out.println("<<< desertTrack cheatmodeSetLocation >>>");
        }
	if ((selection >= 0) && (selection <= 24)){ // ensure valid location between 0-24
	    int oldLocation = sportsCar.getLocation(); // store temporary location
	    sportsCar.setLocation(selection); // store the new location
	    setLocation(sportsCar, selection); // set the new location on the array
	    getTrack()[oldLocation]=null; // wipe the old location
	}
    }
}
