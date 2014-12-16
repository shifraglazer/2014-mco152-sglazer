package glazer.acm2;

import java.util.Scanner;

public class RascalTriangle {

	public int findValue(int row, int index) {
		if (row < 0 || index < 0 || index > row) {
			return 0;
		} else if (row == 0 || index == 0 || row == index) {
			return 1;
		} else if (index == 1 || index == row - 1) {
			return row;
		} else {
			int value = 1;
			for (int i = 0; i < row - index; i++) {
				value += index;
			}
			return value;
		}
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int dataSets = keyboard.nextInt();
		int row;
		int indexRow;
		int indexItem;
		RascalTriangle triangle;
		for (int i = 0; i < dataSets; i++) {
			row = keyboard.nextInt();
			indexRow = keyboard.nextInt();
			indexItem = keyboard.nextInt();
			if (indexRow != 0 && indexItem != 0 && indexRow != indexItem - 1) {
				triangle = new RascalTriangle();
				System.out.println(row + " "
						+ triangle.findValue(indexRow, indexItem));
			} else {
				System.out.println(row + " " + 1);
			}

		}
		keyboard.close();
	}
}
