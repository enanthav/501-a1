/*
  Version: 3.5
  Specific Features: This is a child of the parent class Track with the additional ability to generate a blizzard. A car travelling during a blizzard will be stuck (it will not move forward) yet it will still expend fuel amounts appropriate to the type of car and driving mode. The exception is an SUV travelling in AWD (it will move a distance of 1). Each turn has a 10% chance of a blizzard occurring, otherwise the driving conditions will be normal.
  Limitations: The program is presently storing ASCII values in local variables. There is likely a more efficient way to compare user input to the ASCII values without storing them as local variables.
*/
public class ArcticTrack extends Track{
    private boolean blizzardModeOn;
    private SUV anSUV = new SUV('V'); // create an instance of SUV

    // This constructor sets the location on the array of the SUV to 0
    public ArcticTrack(){
	    setLocation(anSUV, 0);
    }

    // This sets the blizzard to true
    public void setBlizzardMode(){
        blizzardModeOn = true;
        System.out.println("Blizzard mode is on");
	    if (Debug.getOn() == true){
	        System.out.println("Blizzard mode is on");
	    }
    }

    // Turns blizzard off
    public void turnBlizzardOff(){
        blizzardModeOn = false;
	    if (Debug.getOn() == true){
	        System.out.println("Blizzard mode is off");
	    }
    }

    // Returns the boolean blizzardModeOn
    public boolean getBlizzardMode(){
	    return(blizzardModeOn);
    }

    // Displays driving menu for the SUV
    public void drivingMenu(){
	    System.out.println("SUV Driving Options");
	    System.out.println("-------------------");
	    System.out.println("(a)ll wheel drive mode");
	    System.out.println("(d)rive normally");
	    System.out.println("(q)uit simulation");
	    System.out.println("Enter selection: ");
    }

    // Performs the functions necessary for the car to move along the track
    public boolean moveCar(int selection){
        if (Debug.getOn() == true){
            System.out.println("<<< Arctic.moveCar() >>>");
        }
        int A = 65; int a = 97; // ASCII values for 'A', 'a', 'D', 'd'
        int D = 68; int d = 100;
	    boolean validInput = false; // will return true if the user input is valid
	    if ((selection == A) || (selection == a) || (selection == D) || (selection == d)){ // check that the user input is valid
	        anSUV.drivingMode(selection); // updates driving mode to AWD or normal depending on user choice
	        validInput = true;
            }
            if ((anSUV.getFuel() >= SUV.CONSUMPTION_RATE) && (validInput == true)){ // check to ensure there is enough fuel for a move
	            if ((blizzardModeOn == false) && (anSUV.getNormalMode() == true)){ // if blizzard mode is off and normal mode is on
                    int distance = anSUV.move(); // stores appropriate move distance of SUV in a temporary variable
                    updateGrid(distance); // updates grid accordingly using the appropriate move distance
                    System.out.println("Distance moved: " + distance + "\n");
	            }
	            else if ((blizzardModeOn == true) && (anSUV.getNormalMode() == true)){ // if blizzard mode is on and normal mode is on, the vehicle does not move     
		            System.out.println("The SUV is unable to move. It is stuck in a blizzard!");
		        }
	            else if (anSUV.getNormalMode() == false){ // checks if normal mode is off
		            int distance = anSUV.moveAWD(); // moves SUV using AWD distance
		            updateGrid(distance); // updates grid with using appropriate distance travelled in AWD
                    System.out.println("Distance moved: " + distance + "\n");
                }
	        }
            else if(anSUV.getFuel() < SUV.CONSUMPTION_RATE){ // if there isn't enough fuel to move, display message to user
	            System.out.println("There is not enough fuel to move!");
            }
        return(validInput); // will return true if the user input was valid
    }

    // Update the grid with the new location of the car and wipe the old location
    public void updateGrid(int newDistance){
        int oldLocation = anSUV.getLocation();
        if (((anSUV.getLocation() + anSUV.AWD_DISTANCE) <= SIZE - 1) || ((anSUV.getLocation() + Car.STANDARD_DISTANCE) <= SIZE - 1)){ // if the new location is less than or equal to the array size, update the grid
            anSUV.trackLocation(anSUV, newDistance); // update location attribute
            getTrack()[anSUV.getLocation()] = anSUV; // update array with new car location
            getTrack()[oldLocation] = null; // wipe old location
        }
        if (Debug.getOn() == true){
            System.out.println("<<< updateGrid() >>>");
            System.out.println(oldLocation + "OLD LOCATION");
            System.out.println(anSUV.getLocation() + "NEW LOCATION"); 
        }
    }
    // Returns reference to SUV
    public SUV getSUV(){
	    return(anSUV);
    }

    // Processes cheatmode set location action
    public void cheatmodeSetLocation(int selection){
	if ((selection >= 0) && (selection <= Track.SIZE-1)){ // ensures the location is valid before changing
	    int oldLocation = anSUV.getLocation(); // stores old location in temporary variable
	    setLocation(anSUV, selection); // update new location on the array
	    anSUV.setLocation(selection); // store new location
	    getTrack()[oldLocation] = null; // set old Location to null
	}
    }
}
