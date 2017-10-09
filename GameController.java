/*
  Name: Elizabeth Nanthavong (00326374)
  Tutorial: 08
  Version: 3.0
  Specific Features: This class is responsible for input and output: displaying the appropriate menu, getting user input, validating input, etc.). It is directly and indirectly responsible for carrying these actions by either calling the appropriate method in another class. It also determines the outcome of  the simulation (i.e. which car won, whether the simulation has ended).
  Limitations: When a character or String is input into cheat menu, the program crashes. Currently unable to quit from cheat menu (since it only takes in integers).
*/

import java.util.Scanner;
import java.util.Random;
public class GameController{

    Scanner in = new Scanner(System.in);
    DesertTrack desert;
    ArcticTrack arctic;
    public final int CHANCE_OF_WEATHER = 10;

    // Displays instructions for the game
    public void gameInstructions(){
	System.out.println("SPEED\n-----\nSpeed is a 2 player racing simulation with two driving tracks: arctic and desert. The arctic track contains an SUV and the desert track contains the sports car. The SUV is able to travel in AWD or normal mode. If a blizzard hits the arctic track, but the SUV is not in AWD, the SUV will not move. If a heatwave strikes the desert track, the sports car will consume double the fuel (fuel consumption will be 2 versus 4). Each track has a 10% chance of a weather condition striking it. Each car will race to the end of its respective track without running out of fuel first. Good luck!!\nPress enter to continue");
    in.nextLine();
    }

    // Determines the outcome of the simulation, a boolean will be returned true if the game is over (i.e. a win or a draw)
    public boolean checkGameOver(){
	    boolean gameOver = false;
	    if ((arctic.isWon() == true) && (desert.isWon() == true)){ // check for a tie!
	        gameOver = true;
	        System.out.println("Tie! The SUV and sports car have reached the end of the track at the same time.");
	    }
	    else if (arctic.isWon() == true){ // check if the SUV has won
	        gameOver = true;
	        System.out.println("You have won!!!!");
	    }
	    else if (desert.isWon() == true){ // check to see if the sports car has won
	        gameOver = true;
	        System.out.println("You have lost!!!");
	    }
	    else{
	        gameOver = false;
	    }
	    return(gameOver); // will return true if the game has been won, lost, or tied
    }	
 
    // Display cheat menu, cheatMenuSelected is a boolean that will return true if the user selected the cheat menu
    public boolean cheatMenu(int choice){                          // Character.getNumericValue(char)
        int c = 99; // ASCII value for "c"
	boolean cheatMenuSelected = false;
	boolean validInput = false; // returns true if the person selected an integer between 0-6
        if (choice == c){
            do{
	            	printCheatMenu();
		        int cheatSelection = in.nextInt();
	            if (processCheatMenu(cheatSelection) == true){
	                validInput = true;
	            }
            }
            while (validInput == false); // if the input is incorrect, reprompt the user for input
        cheatMenuSelected = true;
        }
	return(cheatMenuSelected); // will return true if the user entered the cheat menu
    }
    
    public void printCheatMenu() {
        System.out.println("~CHEAT MENU SELECTION~");
        System.out.println("(0) Toggle debugging messages on/off");
        System.out.println("(1) Change fuel of sports car");
        System.out.println("(2) Change fuel of SUV car");
        System.out.println("(3) Change location of sports car");
        System.out.println("(4) Change location of SUV car");
        System.out.println("(5) Make a blizzard in the arctic track");
        System.out.println("(6) Make a heatwave in the desert track");
    		System.out.println("Enter selection: ");
    }
    
    // Process the cheat menu based on the user selection based on cases
    public boolean processCheatMenu(int selection){
        int choice = 0;
        boolean valid = false;
        switch(selection){
        case 0: Debug.setOn();
            valid = true;
            break;
        case 1: System.out.println("~CHANGE THE SPORTS CAR FUEL~ \nEnter a value between 0 and 30 \nEnter amount of fuel: ");
	        choice = in.nextInt();
	        changeSportsCarFuel(choice); // process the user's input for the new fuel
            valid = true;
	    break;
        case 2: System.out.println("~CHANGE THE SUV FUEL~ \nEnter a value  between 0 and 50 \nEnter amount of fuel: ");
            choice = in.nextInt();
            changeSUVFuel(choice); // process the user's input for the new fuel
            valid = true;
            break;
        case 3: System.out.println("~MOVE THE SPORTS CAR~ \nMove the car from location 0 to 24 \nEnter Location: ");
	        choice = in.nextInt();
	        moveSportsCar(choice); // process the user's input for the new location
            valid = true;
            break;
        case 4: System.out.println("~MOVE THE SUV~ \nMove the car from location 0 to 24 \nEnter location: ");
	        choice = in.nextInt();
	        moveSUV(choice); // process the user's input for the new location
	        valid = true;
	        break;
        case 5: arctic.setBlizzardMode(); // turn blizzard on
            valid = true;
            break;
	    case 6: desert.turnHeatWaveOn(); // turn heatwave on
            
            break;
        default: System.out.println("Invalid input! Please enter an integer between 0-6");
        }
        return(valid); // return true if the user input was valid
    }

    // Allows user to change sports car location via cheat menu
    public void moveSportsCar(int newLocation){
	    int choice = newLocation;
	    boolean validInput = false;
	    do{
	        if ((choice >= 0) && (choice <= 24)){ // ensure fuel is between 0 and 24 before changing location
		        validInput = true;
		        desert.cheatmodeSetLocation(choice); // change location to what user input
	        }
	        else{ // reprompt user while input is invalid
		        System.out.println("Please enter a value between 0 and 24");
		        choice = in.nextInt();
		        validInput = false;
	        }
	}
	while(validInput == false);
    }
    // Allows user to change SUV location via cheat menu
    public void moveSUV(int newLocation){
	int choice = newLocation;
	boolean validInput = false;
	do{
	    if ((choice >= 0) && (choice <= 24)){ // ensure input is between 0 and 24 before changing location
	    	validInput = true;
		arctic.cheatmodeSetLocation(choice); // change location to what user input
	    }
	    else{ // reprompt user while input is invalid
		System.out.println("Please enter a value between 0 and 24");
		choice = in.nextInt();
		validInput = false;
	    }
	}
	while(validInput == false); // reprompt while input is invalid
    }

    
    // Allows user to change SUV  fuel via cheat menu
    public void changeSUVFuel(int fuel){
	int choice = fuel;
	boolean validInput = false;
	do{
	    if ((choice >= 0) && (choice <= 50)){ // ensures the fuel is between 0 and 50 before changing it
		    validInput = true;
		    arctic.getSUV().changeFuel(choice); // changes fuel
	    }
	    else{
		    System.out.println("Please enter a value between 0 and 50!");
		    choice = in.nextInt();
		    validInput = false;
	    }
	}
	while(validInput == false); 
        if (Debug.getOn() == true){
            System.out.println("SUV fuel is currently " + arctic.getSUV().getFuel());
        }
    }

    // Allows user to change spors car fuel via cheat menu
    public void changeSportsCarFuel(int fuel){
	    int choice = fuel;
	    boolean validInput = false;
	    do{
	        if ((choice >= 0) && (choice <=30)){ // ensure choice is between 0 and 30
		    validInput = true;
		    desert.getSportsCar().changeFuel(choice); // set new fuel
	        }
	        else{ // else reprompt user for valid input
		    System.out.println("Please enter a value between 0 and 30!");
		    choice = in.nextInt();
		    validInput = false;
	        }
	    }
	    while(validInput == false); // continue to reprompt if input is invalid
            if (Debug.getOn() == true){
	        System.out.println("<<< changeSportsCarFuel() >>>>");
                System.out.println("Sports car fuel is currently " + desert.getSportsCar().getFuel());
            }
    }
      
    // Generate weather probability through a random number generator
    public boolean generateWeatherProbability(){
	    Random generator = new Random();
	    int number = generator.nextInt(101)+1;
	    boolean chance = false;
	    if (number <= CHANCE_OF_WEATHER){ // if the number is less than or equal to 10, the chance of a weather condition is true
	        chance = true;
	    }
	    else{ // if generated number, no weather condition will occur
	        chance = false;
	    }
	    return(chance);
    }

    // If the generated number is less than or equal 10, then turn the heatwave on
    public void chanceHeatwave(){
        if (generateWeatherProbability() == true){ // check to see if random number is less than or equal to 10
	        desert.turnHeatWaveOn();
	        System.out.println("A heatwave has struck the desert track!");
        }
        else{ // if number > 10, turn heatwave off
            desert.turnHeatwaveOff();
        }
    }

    // If generated number is less than or equal to 10, then turn blizzard mode on
    public void chanceBlizzard(){
        if (generateWeatherProbability() == true){ // check to see if random number is less than or equal to 10
            arctic.setBlizzardMode();
            System.out.println("A blizzard has appeared in the arctic track!");
        }
        else{ // if number > 10, turn blizzard off
            arctic.turnBlizzardOff();
        }
    }

    // Displays arctic and desert track
    public void displayTrack(){
        System.out.println("ARCTIC TRACK");
        arctic.display();
        System.out.println("DESERT TRACK");
        desert.display();
    }
    
    // Reprompts for input and calls appropriate methods to move the SUV
    public boolean moveSUV(){
        // System.out.println("<<< MOVE SUV >>>");
        int finalChoice = 0; int quit = 113;
        String tempChoice = null;
        boolean repromptNeeded = false;
        boolean userQuit = false;
        do{
            arctic.drivingMenu();
            tempChoice = in.next();
            finalChoice = (int) tempChoice.charAt(0);
            if (finalChoice == quit){ // If the user chose to quit, break the loop
                userQuit = true;
                break;
            }        
            if ((cheatMenu(finalChoice) == false) && (arctic.moveCar(finalChoice) == false)){ // if the cheatmenu was not selected and the car did not move, reprompt
                repromptNeeded = true;
                System.out.println("Invalid input! Please try again!");
            }
        }
        while(repromptNeeded == true);
        return(userQuit); // returns true if the user decided to quit
    }
    // Allows user to move sports car outside of cheat mode
    public boolean moveSportsCar(){
        int finalChoice = 0; int quit = 113; // quit stores the ASCII value for q
        String tempChoice = null;
        boolean userQuit = false;
        boolean repromptNeeded = false;
         do{
            in.nextLine();
            desert.printDrivingMenu();
            tempChoice = in.nextLine(); // store input as a String
            finalChoice = (int) tempChoice.charAt(0); // take first character as value
            if (finalChoice == quit){ // end game if user chose to quit
                userQuit = true;
                break;
            }
            if ((cheatMenu(finalChoice) == false) && (desert.moveCarOnTrack(finalChoice) == false)){ // checks to see if cheat menu was entered and if the sports car was moved, if none oare true, reprompt for input
                repromptNeeded = true;
                System.out.println("Invalid input! Please try again!");
                }
        }
        while(repromptNeeded == true);
	 return(userQuit); // returns true if the user decided to quit
    }

    // Main game loop, calls appropriate methods from ArcticTrack, DesertTrack and GameController
    public void start(){
        gameInstructions();
        int quit = 113; // ASCII for q
	    int finalChoice = 0;
	    desert = new DesertTrack();
	    arctic = new ArcticTrack();
	    do{
            displayTrack();
            if (moveSUV() == true){ // move the SUV, moveSUV() returns true if user chose to quit
                break;
            }    
            chanceBlizzard(); // check if blizzard occurred, if it has, it will affect the next turn
            if (moveSportsCar() == true){ // move the sports car, moveSportsCar() returns true if person chose to quit
                break;
            }
	    chanceHeatwave(); // check to see if there is a heatwave
        }
	    while (checkGameOver() == false); // continue running the game until we have a winner/loser/draw
        displayTrack();
    }
}
