/*
  Version: 3.0
  Specific Features: This is a child of the parent class Track with an additional ability to generate a heatwave. A sports car travelling during a heat wave will overheat. The effect of overheating is consuming four fuel points versus 2. Each turn there's a 10% chance of a heatwave occurring, otherwise the temperature is normal.
*/
public class DesertTrack extends Track{
    private Sports sportsCar;
    private boolean heatwaveModeOn;

    public DesertTrack(){
    		sportsCar = new Sports('S');
        setLocation(sportsCar, 0);
    }

    public Sports getSportsCar(){
	    return(sportsCar);
    }

    public void turnHeatWaveOn(){
	    if (Debug.getOn() == true){
	        System.out.println("Heatwave mode is on");
	    }
	    heatwaveModeOn = true;
    }

    public void turnHeatwaveOff(){
	    if (Debug.getOn() == true){
	        System.out.println("Heatwave mode is off");
	        }
        heatwaveModeOn = false;
    }

    public boolean getHeatwaveMode(){
    		return(heatwaveModeOn);
    }

    public void printDrivingMenu(){
	    System.out.println("Sportscar Driving Options");
	    System.out.println("-------------------------");
	    System.out.println("(d)rive normally");
	    System.out.println("(q)uit simulation");
	    System.out.println("Enter selection: ");
    }

    public boolean moveCarOnTrack(int userChoice){
        if (Debug.getOn() == true){
            System.out.println("<<< Desert.moveCar() >>>");
        }
        int asciiD = 68; int asciid = 100;
	    boolean isValidInput = false;
	    if ((userChoice == asciiD) || (userChoice == asciid)){
                isValidInput = true;
                if ((sportsCar.getFuel() >= Sports.CONSUMPTION_RATE) && (heatwaveModeOn == false)){ // check to ensure there is enough fuel for a move in normal weather
                    sportsCar.move(heatwaveModeOn);
                    updateTrack(Sports.STANDARD_DISTANCE); 
                    System.out.println("Distance moved: " + Sports.STANDARD_DISTANCE + "\n");
                    }
                else if ((sportsCar.getFuel() >= sportsCar.HEATWAVE_FUEL_CONSUMPTION) && (heatwaveModeOn == true)){ // check to ensure that there is greater than or equal to 4 fuel when heatwave is on
                    sportsCar.move(heatwaveModeOn); 
                    updateTrack(Sports.STANDARD_DISTANCE);
                    System.out.println("Distance moved: " + Sports.STANDARD_DISTANCE + "\n");
                }
                else{
                    System.out.println("There is not enough fuel to move!");
                }
            }
        return(isValidInput);
    }

    
    public void updateTrack(int newDistance){
        int oldLocation = sportsCar.getLocation();
        sportsCar.trackLocation(sportsCar, newDistance); // track new location of the sports car
        getTrack()[sportsCar.getLocation()] = sportsCar; // update array with sports car object
        getTrack()[oldLocation] = null;

        if (Debug.getOn() == true){
            System.out.println("<<< sportsCar.updateGrid() >>>");
            System.out.println(oldLocation + " OLD LOCATION");
            System.out.println(sportsCar.getLocation() + " NEW LOCATION"); 
        }
    }

    public void cheatmodeSetLocation(int userChoice){
        if (Debug.getOn() == true){
            System.out.println("<<< desertTrack cheatmodeSetLocation >>>");
        }
		if ((userChoice >= 0) && (userChoice <= SIZE-1)){
		    int oldLocation = sportsCar.getLocation();
		    sportsCar.setLocation(userChoice);
		    setLocation(sportsCar, userChoice);
		    getTrack()[oldLocation]=null;
		}
    }
}
