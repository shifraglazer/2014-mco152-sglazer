package glazer.acm2;

import java.util.Scanner;

public class MaxSum {
	private int maxSum;
	int[][] array;

	public MaxSum(Scanner keyboard) {

		int n = keyboard.nextInt();
		array = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				array[i][j] = keyboard.nextInt();
			}
		}
		maxSum = array[0][0];
	}

	public int getMax() {
		
		int sum;
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array[i].length; j++) {
				for (int k = 0; k < array.length; k++) {
					sum = 0;
					for (int l = k; l < array.length; l++) {
						sum = getSum(i, j, k, l);
						if (sum > maxSum) {
							maxSum = sum;
						}
					}
				}
			}
		}
		return maxSum;
	}

	public int getSum(int min, int max, int rowsMin, int rowsMax) {

		int sum = 0;
		for (int i = min; i <= max; i++) {
			for (int j = rowsMin; j <= rowsMax; j++) {
				sum += array[i][j];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// start with 0 and go to <n
		// min 0 to max
		// min 0 to max
		// min 0 to max
	

		
		Scanner keyboard = new Scanner(System.in);
		MaxSum max = new MaxSum(keyboard);
		long startTime=System.currentTimeMillis();
		System.out.println(max.getMax());
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}

}
