package JUnitTesting;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

import Character.*;
import Item.*;
/**
 * This JUnit class is responsible for testing both positive and negative cases for all public methods under Player class.
 * @author John
 *
 */
public class PlayerTest {
	
	List<Item> items = new ItemGenerator().getItemList();
	Player player = new Player(10, 10, 1, 2, 3);
	
	@Test
	public void checkInventoryKeyItemPos() {
		player.pickUp(items.get(6));
		assertTrue(player.checkInventoryKeyItem(items.get(6)));
	}
	
	@Test
	public void checkInventoryKeyItemNeg() {
		player.pickUp(items.get(3));
		assertFalse(player.checkInventoryKeyItem(items.get(3)));
	}
	
	@Test
	public void pickUpPos() {
		try {
			player.pickUp(items.get(5));
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Item does not exist, throws ArrayIndexOutOfBoundsException.");
		}
	}
	
	@Test
	public void pickUpNeg1() {
		try {
			player.pickUp(items.get(-1));
		} 
		catch (IndexOutOfBoundsException e) {
			System.out.println("Item does not exist, throws ArrayIndexOutOfBoundsException.");
		}
		
	}
	
	@Test
	public void pickUpNeg2() {
		try {
			player.pickUp(items.get(15));
		} 
		catch (IndexOutOfBoundsException e) {
			System.out.println("Item does not exist, throws IndexOutOfBoundsException.");
		}
		
	}

	@Test
	public void useItemPos() {
		player.pickUp(items.get(5));
		player.pickUp(items.get(3));

		player.useItem(0);
		
	}
	
	@Test
	public void useItemNeg() {
		player.pickUp(items.get(5));
		player.pickUp(items.get(3));
		
		player.useItem(-1);		//throws IndexOutOfBoundsException

	}
	
	@Test
	public void equipPos() {
		player.pickUp(items.get(0));
		player.pickUp(items.get(2));
		
		player.equip(0);
	}
	
	@Test
	public void equipNeg() {
		player.pickUp(items.get(0));
		player.pickUp(items.get(2));
		
		player.equip(-1);	//throws IndexOutOfBoundsException
	}
	
}
