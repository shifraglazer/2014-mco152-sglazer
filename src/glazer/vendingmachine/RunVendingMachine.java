package glazer.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {
		Money bank = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		String inventoryFilename = "inventory.txt";
		try {
			inventory.load(inventoryFilename);
		} catch (IOException e) {
			System.out.println("Error reading file");
		}
		VendingMachine machine = new VendingMachine(inventory, bank);
		boolean dispensed = false;
		System.out.println(inventory);
		System.out
				.println("Add Money/Make Selection?\n1.Dollar\n2.Quarter\n3.Dime\n4.Nickle\nor enter in the Item Code");
		Scanner keyboard = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("$0.00");
		String choice;
		System.out.println("Balance "
				+ formatter.format(machine.getPaid().getTotal()));

		do {
			choice = keyboard.next();
			while ("1".equals(choice) || "2".equals(choice)
					|| "3".equals(choice) || "4".equals(choice)) {

				switch (choice) {
				case "1": {
					machine.pay(new Money(1, 0, 0, 0));
					break;
				}
				case "2": {
					machine.pay(new Money(0, 1, 0, 0));
					break;
				}
				case "3": {
					machine.pay(new Money(0, 0, 1, 0));
					break;
				}
				case "4": {
					machine.pay(new Money(0, 0, 0, 1));
					break;
				}
				}
				System.out.println("Balance "
						+ formatter.format(machine.getPaid().getTotal()));
				choice = keyboard.next();
			}
			try {

				Money money = machine.buy(choice.toUpperCase());
				System.out.println("Dispensing "
						+ inventory.get(choice.toUpperCase()).getName());
				System.out.println("Change "
						+ formatter.format(money.getTotal()));
				dispensed = true;

			} catch (CodeNotFoundException e) {
				System.out.println(e.getMessage());

			} catch (NotEnoughPaidException e) {
				System.out.println(e.getMessage());

			} catch (NotEnoughChangeException e) {
				System.out.println(e.getMessage());
			}
		} while (!dispensed);
		keyboard.close();
	}

}
