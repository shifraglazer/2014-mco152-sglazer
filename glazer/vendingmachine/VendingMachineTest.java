package glazer.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void testPay() throws IOException {

		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine machine = new VendingMachine(inventory, money);
		Assert.assertEquals(2.65, machine.pay(new Money(2, 0, 6, 1)), 0);
		Assert.assertEquals(2.65, machine.getPaid().getTotal(), 0);
	}

	@Test
	public void testBuy() throws IOException, CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine machine = new VendingMachine(inventory, money);
		// correct change
		machine.pay(new Money(2, 0, 6, 2));
		Assert.assertEquals(1.4, machine.buy("B02").getTotal(), 0);
		// paid returns to 0
		Assert.assertEquals(0, machine.getPaid().getTotal(), 0);
		// reduce qty
		Assert.assertEquals(2, inventory.get("B02").getQuantity(), 0);

	}

	@Test
	public void testCodeNotFoundException() throws IOException,
			NotEnoughPaidException, NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine machine = new VendingMachine(inventory, money);
		machine.pay(new Money(4, 0, 0, 0));
		try {
			machine.buy("H04");
			Assert.fail();
		} catch (CodeNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotEnoughPaidException() throws IOException,
			CodeNotFoundException, NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine machine = new VendingMachine(inventory, money);
		machine.pay(new Money(0, 1, 0, 0));
		try {
			machine.buy("A01");
			Assert.fail();
		} catch (NotEnoughPaidException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotEnoughChangeException() throws IOException,
			CodeNotFoundException, NotEnoughPaidException {
		Money money = new Money(0, 0, 0, 5);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");

		VendingMachine machine = new VendingMachine(inventory, money);
		machine.pay(new Money(2, 1, 0, 0));
		try {
			machine.buy("A01");
			Assert.fail();
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotEnoughChangeException2() throws IOException,
			CodeNotFoundException, NotEnoughPaidException {
		Money money = new Money(2, 0, 0, 0);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");

		VendingMachine machine = new VendingMachine(inventory, money);
		machine.pay(new Money(2, 0, 0, 0));
		try {
			machine.buy("A01");
			Assert.fail();
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSuccessfulTransaction() throws IOException,
			CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		Money money = new Money(0, 0, 0, 21);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");

		VendingMachine machine = new VendingMachine(inventory, money);
		machine.pay(new Money(2, 1, 0, 0));
		Assert.assertEquals(1.25, machine.buy("C03").getTotal(), 0);
		// bank has right amount of change
		Assert.assertEquals(2.05, money.getTotal(), 0);
		Assert.assertEquals(0, inventory.get("C03").getQuantity(), 0);
		Assert.assertEquals(0, machine.getPaid().getTotal(), 0);

	}

}
