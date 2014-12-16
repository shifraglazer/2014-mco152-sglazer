package glazer.acm2;

import java.util.HashSet;
import java.util.Scanner;

public class HappyPrimes {
	HashSet<String> set;

	public HappyPrimes() {
		set = new HashSet<String>();
	}

	public String getNextNumber(String original) {
		set.add(original);
		String prime = calculateSum(original);

		return prime;
	}

	public boolean isPrime(String original) {
		int number = Integer.valueOf(original);
		if (number <= 2) {
			return false;
		}
		for (int i = 2; i < number/2; i++) {
				if (number%i==0) {
					return false;
				}

		}
		return true;
	}

	public boolean setContains(String num) {
		return set.contains(num);
	}

	public String calculateSum(String number) {
		String prime = number;
		int sum = 0;
		for (int j = 0; j < prime.length(); j++) {
			Integer num = Integer.valueOf(prime.charAt(j));
			sum += (int) Math.pow(num.doubleValue(), 2.0);

		}
		// System.out.println(prime + " " + sum);
		prime = String.valueOf(sum);
		return prime;

	}

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		int rows = keyboard.nextInt();
		int lineNum;
		String prime;
		String found;
		String original;
		HappyPrimes happy;
		for (int i = 0; i < rows; i++) {
			happy = new HappyPrimes();
			lineNum = keyboard.nextInt();
			prime = keyboard.next();
			original = prime;
			found = "NO";
			
				do {
					prime = happy.getNextNumber(prime);
				} while (Integer.valueOf(prime) != 1
						&& !happy.setContains(prime));
				if (Integer.valueOf(prime) == 1) {
					if (happy.isPrime(original)) {
					found = "YES";
				}
			}
			System.out.println(lineNum + " " + original + " " + found);

		}
		keyboard.close();
	}

}
