package glazer.vendingmachine;

import java.math.BigDecimal;

public class VendingMachine {

	private Inventory inventory;
	private Money bank;

	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;

	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}

	public Money getPaid() {
		return paid;
	}

	/**
	 * Add additional Money to the machine
	 * 
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return additional.getTotal();
	}

	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException
	 *             if there is no item with that code
	 * @throws NotEnoughPaidException
	 *             if paid is not enough to buy the item
	 * @throws NotEnoughChangeException
	 *             if the transaction cannot be completed because there isn't
	 *             enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException,
			NotEnoughPaidException, NotEnoughChangeException {

		BigDecimal extraMoney;
		code = code.toUpperCase();
		Item item = inventory.get(code);

		if (item != null) {
			if (inventory.isEmpty(code)) {
				return new Money(0, 0, 0, 0);
			}
			bank.add(paid);
			BigDecimal price = BigDecimal.valueOf(item.getPrice());
			BigDecimal total = BigDecimal.valueOf(paid.getTotal());
			if (total.equals(price)) {
				inventory.removeOne(code);
				paid = new Money(0, 0, 0, 0);
				return new Money(0, 0, 0, 0);
			} else if (total.compareTo(price) < 0) {
				throw new NotEnoughPaidException();
			} else if (total.compareTo(price) > 0) {

				extraMoney = total.subtract(price);
				double extra = extraMoney.doubleValue();
				Money change = bank.remove(extra);
				if (change.getTotal() == extra) {
					inventory.removeOne(code);
					paid = new Money(0, 0, 0, 0);
					return change;
				} else {
					bank.remove(paid.getTotal());
					paid = new Money(0, 0, 0, 0);
					throw new NotEnoughChangeException();
				}
			}

		}

		else {
			throw new CodeNotFoundException();
		}
		return null;
	}

}
