import static org.junit.Assert.*;

import java.io.Console;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpeedTest {
	DesertTrack desert;
	ArcticTrack arctic;
	Sports sportsCar;
	SUV suv;
	GameController controller;
	
	@Before
	public void initializeGame() {
        desert = new DesertTrack();
        arctic = new ArcticTrack();
        sportsCar = new Sports('V');
        suv = new SUV('S');
        controller = new GameController();
	}
	
	@Test
	public void checkValidInputCheatMenu() throws Exception {
		assertEquals(true, controller.processCheatMenu(0));
		assertEquals(false, controller.processCheatMenu(-1));
		assertEquals(false, controller.processCheatMenu(7));
	}
	
    @Test
    public void setLocationToBeginning() throws Exception {
        sportsCar.setLocation(0);
        assertEquals(0, sportsCar.getLocation());
        suv.setLocation(0);
        assertEquals(0, suv.getLocation());
    }

    @Test
    public void setLocationToMiddle() throws Exception {
        sportsCar.setLocation(14);
        assertEquals(14, sportsCar.getLocation());
        suv.setLocation(14);
        assertEquals(14, suv.getLocation());
    }
    
    @Test
    public void setLocationToEnd() throws Exception {
        sportsCar.setLocation(23);
        assertEquals(23, sportsCar.getLocation());
        suv.setLocation(23);
        assertEquals(23, suv.getLocation());
    }

    @Test
    public void checkIfNoWin() throws Exception{
		sportsCar.setLocation(10);
		desert.updateGrid(10);
		suv.setLocation(11);
		arctic.updateGrid(11);
		System.out.println(arctic.isWon());
		System.out.println(desert.isWon());
    }
    
    @Test
    public void checkIfSportsCarWins() throws Exception {
    		sportsCar.setLocation(24);
    		desert.updateGrid(24);
    		assertEquals(true, desert.isWon());
    }
   
    @Test
    public void checkIfSUVWins() throws Exception {
		suv.setLocation(24);
		arctic.updateGrid(24);
		assertEquals(true, arctic.isWon());
    }

	@After
	public void cleanUpGame() {
        desert = null;
        arctic = null;
        sportsCar = null;
        suv = null;
        controller = null;
	}
}
