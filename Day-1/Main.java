import java.util.Scanner;
import java.io.*;

class Main {
  public static void main(String[] args) {

		int[] enteries = new int[200];

		try {
			Scanner stan = new Scanner(new File("input.txt"));

			for (int i = 0; stan.hasNext(); i++) {
				enteries[i] = stan.nextInt();
			}
			
			stan.close();

		} catch (FileNotFoundException e) {
			System.err.println("ERR: File not found!");
		}

		// Part 1
		System.out.println("--- Part 1 ---");
		
		for (int i = 0; i < enteries.length; i++) {
			for (int j = i; j < enteries.length; j++) {
				if (enteries[i] + enteries[j] == 2020) {
					System.out.println(enteries[i] + " + " + enteries[j] + " = 200");
					System.out.println("The solution is " + enteries[i]*enteries[j]);
				}
			}
		}
		
		System.out.println("\n\n");

		//Part 2
		System.out.println("--- Part 2 ---");

		for (int i = 0; i < enteries.length; i++) {
			for (int j = i; j < enteries.length; j++) {
				for (int k = j; k < enteries.length; k++) {
					if (enteries[i] + enteries[j] + enteries[k] == 2020) {
						System.out.println(enteries[i] + " + " + enteries[j] + " + " + enteries[k] + " = 200");
						System.out.println("The solution is " + enteries[i]*enteries[j]*enteries[k]);
					}
				}
			}
		}
	}
}