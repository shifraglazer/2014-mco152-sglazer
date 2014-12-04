package glazer.acm2;

import java.util.Scanner;

public class HappyPrimes {

	public HappyPrimes() {

	}

	public String getNextNumber(String original) {
		return calculateSum(original);
	}

	public boolean isPrime(String original) {
		int number = Integer.valueOf(original);
		if (number <= 2) {
			return false;
		}
		for (int i = 2; i < number; i++) {
			for (int j = 2; j < number; j++) {
				if (i * j == number) {
					return false;
				}
			}

		}
		return true;
	}

	public String calculateSum(String number) {
		String prime = number;
		int sum = 0;
		for (int j = 0; j < prime.length(); j++) {
			Integer num = Integer.valueOf(String.valueOf(prime.charAt(j)));
			sum += (int) Math.pow(num.doubleValue(), 2.0);

		}
		// System.out.println(prime + " " + sum);
		prime = String.valueOf(sum);
		return prime;

	}

	public void getEnd(String prime) {

	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int rows = keyboard.nextInt();
		int lineNum;
		String prime;
		String found;
		String original;
		HappyPrimes happy = new HappyPrimes();
		for (int i = 0; i < rows; i++) {
			lineNum = keyboard.nextInt();
			prime = keyboard.next();
			original = prime;
			found = "NO";
			if (happy.isPrime(original)) {
				do {
					prime = happy.getNextNumber(prime);
				} while ((Integer.valueOf(prime) != 1 && !happy.calculateSum(
						prime).equals(happy.calculateSum(original))));
				if (Integer.valueOf(prime) == 1) {
					found = "YES";
				}
			}
			System.out.println(lineNum + " " + original + " " + found);

		}
		keyboard.close();
	}

}
