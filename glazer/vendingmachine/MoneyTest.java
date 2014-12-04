package glazer.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	public MoneyTest() {

	}

	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		Assert.assertEquals(4, money.remove(4.0).getTotal(), 0);
		Assert.assertEquals(10, money.getTotal(), 0);
		Assert.assertEquals(.8, money.remove(.8).getTotal(), 0);
		Assert.assertEquals(9.2, money.getTotal(), 0);
		money = new Money(0, 0, 0, 100);
		Assert.assertEquals(1.4, money.remove(1.4).getTotal(), 0);
		Assert.assertEquals(3.6, money.getTotal(), 0);
		
	}

	@Test
	public void testNotEnoughChangeException() {
		Money money = new Money(10, 10, 10, 10);
		try {
			money.remove(20);
			Assert.fail();
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		Money money = new Money(10, 10, 10, 10);
		money.add(new Money(1, 1, 5, 1));
		Assert.assertEquals(15.8, money.getTotal(), 0);

	}

	@Test
	public void testGetTotal() {
		Money money = new Money(5, 2, 1, 0);
		Assert.assertEquals(5.6, money.getTotal(), 0);
	}

}
