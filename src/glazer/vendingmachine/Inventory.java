package glazer.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> list;
	private Map<String, Item> items;

	public Inventory() {
		items = new HashMap<String, Item>();
		list = new ArrayList<Item>();
	}

	public void load(String inventoryFilename) throws IOException {
		Scanner input = new Scanner(new File(inventoryFilename));
		String code;
		String name;
		double price;
		int quantity;
		Item aItem;

		while (input.hasNextLine()) {
			input.useDelimiter(",|\n");
			code = input.next();

			name = input.next();
			price = input.nextDouble();

			quantity = input.nextInt();
			aItem = new Item(code, name, price, quantity);
			items.put(code, aItem);
			list.add(aItem);
		}
		input.close();
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		code = code.toUpperCase();
		if (items.containsKey(code)) {
			return items.get(code);
		}
		return null;
	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		list.add(item);
		items.put(item.getCode(), item);

	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		code = code.toUpperCase();

		if (items.containsKey(code)) {
			Item item = items.get(code);
			item.setQuantity(item.getQuantity() - 1);
		}

	}

	/**
	 * 
	 * @param code
	 * @return false if the Item exists and there is at least one quantity,
	 *         otherwise true.
	 */
	public boolean isEmpty(String code) {
		code = code.toUpperCase();
		if (items.containsKey(code)) {
			Item item = items.get(code);
			if (item.getQuantity() >= 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("$#.00");
		StringBuilder builder = new StringBuilder();
		for (Item i : list) {
			builder.append(i.getCode());
			builder.append(" ");
			builder.append(i.getName());
			builder.append(" @ ");
			builder.append(formatter.format(i.getPrice()));
			builder.append(" x ");
			builder.append(i.getQuantity());
			if (!list.get(list.size() - 1).equals(i)) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

}
