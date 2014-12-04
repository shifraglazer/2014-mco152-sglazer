package glazer.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int sets;
		do {
			sets = keyboard.nextInt();
		} while (sets < 1 || sets > 1000);
		int r;
		String s;

		for (int i = 0; i < sets; i++) {

			String t;
			int n = keyboard.nextInt();
			do {
				r = keyboard.nextInt();
			} while (r < 1 || r > 8);
			s = keyboard.next();

			StringBuilder builder = new StringBuilder();
			builder.append(n);
			builder.append(" ");
			for (int j = 0; j < s.length(); j++) {

				for (int k = 0; k < r; k++) {
					builder.append(s.charAt(j));
				}
			}
			t = builder.toString();
			System.out.println(t);

		}
		keyboard.close();
	}

}
