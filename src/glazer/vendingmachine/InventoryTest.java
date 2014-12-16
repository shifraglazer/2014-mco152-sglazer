package glazer.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testLoad() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		Assert.assertEquals(
				"A01 Candy Bar @ $1.55 x 5\nB02 Chips @ $1.30 x 3\nC03 Pretzels @ $1.00 x 1\nD04 Nuts @ $2.25 x 10\nE05 Gum @ $1.75 x 20",
				inventory.toString());
	}

	@Test
	public void testRemoveOne() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		inventory.removeOne("A01");
		Assert.assertEquals(4, inventory.get("A01").getQuantity());
	}

	@Test
	public void testIsEmpty() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");

		Assert.assertTrue(inventory.isEmpty("h07"));
		Assert.assertFalse(inventory.isEmpty("B02"));
	}

}
