package glazer.acm;

import java.util.Scanner;

public class NthLargestValue {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int sets = keyboard.nextInt();
		int num;
		int[] array;
		int lowest;
		int index=0;
		boolean swapped;
		for (int i = 0; i < sets; i++) {
			num = keyboard.nextInt();
			array = new int[10];
			for (int j = 0; j < array.length; j++) {
				array[j]=keyboard.nextInt();
			}
		
			for(int k=0;k<array.length;k++){
				swapped=false;
				lowest=array[k];
				for(int l=k;l<array.length;l++){
					if(array[l]<lowest){
						lowest=array[l];
						index=l;
						swapped=true;
						
					}
				}
				if(swapped){
				array[index]=array[k];
				array[k]=lowest;
				}
			}
			System.out.println(num + " " + array[7]);
		}
		keyboard.close();
		
		
	}

}
