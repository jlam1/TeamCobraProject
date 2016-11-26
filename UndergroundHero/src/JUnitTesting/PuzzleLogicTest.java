package JUnitTesting;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import Character.Player;
import LogicController.PuzzleLogic;
import Puzzle.Puzzle;
import Room.Room;

/**
 * Class: PuzzleLogicTest
 * 
 * @author William Smith
 *
 *
 *         This class uses JUnit to test the public methods from the
 *         PuzzleLogic class.
 *
 */
public class PuzzleLogicTest {

	PuzzleLogic puzLog;
	Scanner input;
	Player player;
	Puzzle puzzle;
	Room room;

	@Before
	public void setUp() throws Exception {

		player = new Player(100, 100, 3, 3, 2);
		puzLog = new PuzzleLogic(input);
	}

	@Test
	public void testPuzzleLogic() {
		assertNotNull(puzLog);
	}

	@Test(expected = NullPointerException.class)
	public void testInitiatePuzzle() {

		puzLog.initiatePuzzle(room, player);
	}

	@Test
	public void testPuzzleLogicGetSolved() {
		boolean t = true;

		boolean t2 = puzLog.getPuzzleSolved();

		assertNotEquals(t, t2);

	}

}
