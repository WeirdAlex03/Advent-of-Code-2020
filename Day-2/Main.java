import java.util.Scanner;
import java.io.*;

class Main {
  public static void main(String[] args) {
		String[] enteries = new String[1000];

		try {
			Scanner stan = new Scanner(new File("input.txt"));

			for (int i = 0; stan.hasNext(); i++) {
				enteries[i] = stan.nextLine();
			}
			
			stan.close();

		} catch (FileNotFoundException e) {
			System.err.println("ERR: File not found!");
		}

		/* Part 1
		int min, max;
		char chr;
		String pass;

		int chars = 0;
		int count = 0;
		for (int i = 0; i < enteries.length; i++) {
			//1-3 a: abcde
			min = Integer.parseInt(enteries[i].substring(0,enteries[i].indexOf('-')));
			max = Integer.parseInt(enteries[i].substring(enteries[i].indexOf('-')+1,enteries[i].indexOf(' ')));
			chr = enteries[i].substring(enteries[i].indexOf(' ')+1,enteries[i].indexOf(':')).charAt(0);
			pass = new String (enteries[i].substring(enteries[i].indexOf(':')+2));
			chars = 0;

			//System.out.println(min+"\t"+max+"\t"+chr+"\t"+pass);

			for (int j = 0; j < pass.length(); j++) {
				if (pass.charAt(j) == chr) {
					chars++;
				}
			}	
			if (chars >= min && chars <= max) {
				count++;
			}
		}
		System.out.println(count);
		*/

		//Part 2
		int min, max;
		char chr;
		String pass;

		int count = 0;
		for (int i = 0; i < enteries.length; i++) {
			//1 has been subtracted from min and max to be the min and max indicies
			min = Integer.parseInt(enteries[i].substring(0,enteries[i].indexOf('-')))-1;
			max = Integer.parseInt(enteries[i].substring(enteries[i].indexOf('-')+1,enteries[i].indexOf(' ')))-1;
			chr = enteries[i].substring(enteries[i].indexOf(' ')+1,enteries[i].indexOf(':')).charAt(0);
			pass = new String (enteries[i].substring(enteries[i].indexOf(':')+2));

			if (pass.charAt(min) == chr ^ pass.charAt(max) == chr) {
				count++;
			}
		}
		System.out.println(count);
  }
}