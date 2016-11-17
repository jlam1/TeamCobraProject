package JUnitTesting;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import Character.Player;
import Game.ResourceData;

/**
 * Class: ResourceDataTest
 * 
 * @author King Lo
 *
 *
 *         This class uses JUnit to test the public methods from the
 *         ResourceData class The public method that exist are saveGame and
 *         loadGame. Public getters and setter are not required to be tested.
 *
 */

public class ResourceDataTest {

	private ResourceData rd;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rd = new ResourceData();
	}

	@Test
	public void testSaveGame() throws Exception {
		Player player = new Player(100, 50, 60, 77, 125);
		rd.setRoomArrayNumber(3); // 1-2
		rd.setPlayer(player);
		try {
			ResourceData.saveGame(rd, "JUnitTester.dat");
			System.out.println("Save Sucessful");
		} finally {
			
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("Room ID: " + rd.getRoomArrayNumber());
		System.out.println("Player Status: \n" + rd.getPlayer());
		System.out.println("---------------------------------------------------------");
		File file = new File("src/JUnitTester.dat");
		System.out.println("Save File Path:");
		System.out.println(file.getAbsolutePath());
		System.out.println("---------------------------------------------------------");
	}

	@Test
	public void testLoadGame() throws ClassNotFoundException, IOException {
		try {

			rd = (ResourceData) ResourceData.loadGame("JUnitTester.dat");
			
				System.out.println("---------------------------------------------------------");
				System.out.println("LOADING ");
				System.out.println("Loading successful.");
				System.out.println();
				System.out.println("Room ID: " + rd.getRoomArrayNumber());
				System.out.println("Player Status: \n" + rd.getPlayer());
		} 
		finally {
		}

		System.out.println("--------------------------------------------------------");
		File file = new File("src/JUnitTester.dat");
		System.out.println("Load File Path:");
		System.out.println(file.getAbsolutePath());
		System.out.println("---------------------------------------------------------");
	}


}
