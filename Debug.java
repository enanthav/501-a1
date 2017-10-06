/*
  Author:  James Tam
  Version: May 3, 2014
  The sole purpose of this class is track if the program is in 
  debugging mode.
  The setOn method was modified by Elizabeth Nanthavong on March 17, 2014.
 */
public class Debug
{
    private static boolean on = false;
    public static boolean getOn() {  return(on); }
    // Turns debug messages on and off
    public static void setOn(){ 
        if (on == true){
	        on = false;
	        System.out.println("Debug mode off");
	    }
	    else{
	        on = true;
	        System.out.println("Debug mode on");
	    }
    }
}
