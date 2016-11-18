package JUnitTesting;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

import Character.*;
import Item.*;
import Puzzle.*;
import Room.*;
import Game.*;

public class JUnit_John {
	
	List<Item> itemList = new ItemGenerator().getItemList();
	Player player = new Player(10, 10, 1, 2, 3);
	
	@Test
	public void checkInventoryKeyItemPositiveCase1() {
		player.pickUp(itemList.get(6));
		assertTrue(player.checkInventoryKeyItem(itemList.get(6)));
	}
	
	@Test
	public void checkInventoryKeyItemPositiveCase2() {
		player.pickUp(itemList.get(7));
		assertTrue(player.checkInventoryKeyItem(itemList.get(7)));
	}
	
	@Test
	public void checkInventoryKeyItemNegativeCase1() {
		player.pickUp(itemList.get(3));
		assertFalse(player.checkInventoryKeyItem(itemList.get(3)));
	}
	
	@Test
	public void checkInventoryKeyItemNegativeCase2() {
		player.pickUp(itemList.get(3));
		assertFalse(player.checkInventoryKeyItem(itemList.get(3)));
	}
	
	@Test
	public void openInventoryPosCase1() {
		
	}
	
	@Test
	public void openInventoryPosCase2() {
		
	}
	
	@Test
	public void openInventoryNegCase1() {
		
	}
	
	@Test
	public void openInventoryNegCase2() {
		
	}

}
