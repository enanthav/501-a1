import static org.junit.Assert.*;


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
	public void testIfNormalDrivingModeOn() throws Exception {
		assertEquals(true, arctic.getSUV().drivingMode(68));
		assertEquals(true, arctic.getSUV().drivingMode(100));
	}
	
	@Test
	public void testIfNormalDrivingModeOff() throws Exception {
		assertEquals(false, arctic.getSUV().drivingMode(65));
		assertEquals(false, arctic.getSUV().drivingMode(96));
	}

	@Test
	public void testTrackLocation() {
		arctic.getSUV().setLocation(0);
		arctic.getSUV().trackLocation(arctic.getSUV(), 2);
		assertEquals(2, arctic.getSUV().getLocation());
		
		arctic.getSUV().setLocation(10);
		arctic.getSUV().trackLocation(arctic.getSUV(), 2);
		assertEquals(12, arctic.getSUV().getLocation());
	}
	
	@Test
	public void testNoMoveOnTrackLocation() {
		arctic.getSUV().setLocation(0);
		arctic.getSUV().trackLocation(arctic.getSUV(), 0);
		assertEquals(0, arctic.getSUV().getLocation());
		
		arctic.getSUV().setLocation(10);
		arctic.getSUV().trackLocation(arctic.getSUV(), 0);
		assertEquals(10, arctic.getSUV().getLocation());
	}
	
	@Test
	public void checkValidInputCheatMenu() throws Exception {
		assertEquals(true, controller.processCheatMenu(0));
		assertEquals(false, controller.processCheatMenu(-1));
		assertEquals(false, controller.processCheatMenu(7));
	}
	
    @Test
    public void setLocationToBeginning() throws Exception {

    }

    @Test
    public void setLocationToMiddle() throws Exception {
		arctic.getSUV().setLocation(10);
        assertEquals(10, arctic.getSUV().getLocation());
        desert.getSportsCar().setLocation(14);
        assertEquals(14, desert.getSportsCar().getLocation());
    }
    
    @Test
    public void setLocationToEnd() throws Exception {
		arctic.getSUV().setLocation(24);
        assertEquals(24, arctic.getSUV().getLocation());
        desert.getSportsCar().setLocation(24);
        assertEquals(24, desert.getSportsCar().getLocation());
    }
   
    @Test
    public void testBlizzardModeOn() throws Exception {
    		arctic.setBlizzardMode();
    		assertEquals(true, arctic.getBlizzardMode());
    }
    
    @Test
    public void testBlizzardModeOff() throws Exception {
    		arctic.turnBlizzardOff();
    		assertEquals(false, arctic.getBlizzardMode());
    }

    
    @Test
    public void testCheatModeSetLocation() throws Exception {
    		arctic.cheatmodeSetLocation(22);
    		assertEquals(22, arctic.getSUV().getLocation());
    		arctic.cheatmodeSetLocation(0);
    		assertEquals(0, arctic.getSUV().getLocation());
    }
    


    @Test
    public void checkIfNoWin() throws Exception{
		sportsCar.setLocation(10);
		desert.updateTrack(10);
		suv.setLocation(11);
		arctic.updateGrid(11);
		System.out.println(arctic.isWon());
		System.out.println(desert.isWon());
    }
    
    @Test
    public void checkIfSportsCarWins() throws Exception {
    		sportsCar.setLocation(24);
    		desert.updateTrack(24);
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
