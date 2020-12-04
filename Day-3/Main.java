import java.util.Scanner;
import java.io.*;

class Main {
  public static void main(String[] args) {
		//[x (cols)][y (rows)]
		char[][] map = new char[323][31];		//Test is [11][11], main is [323][31]

		try {
			Scanner stan = new Scanner(new File("map.txt"));
			String line;

			for (int i = 0; stan.hasNext(); i++) {
				line = stan.nextLine();
				for (int j = 0; j < line.length(); j++)
					map[i][j] = line.charAt(j);
			}
			
			stan.close();

		} catch (FileNotFoundException e) {
			System.err.println("ERR: File not found!");
		}

		/*
		//Part 1
		int trees = 0;
		int j = 0;
		//right 3, down 1
		for (int i = 0; i < map.length; i++) {
			if (map[i][j] == '#')
				trees++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 3) % map[i].length; //Loop back around to emulate tiling
		}

		System.out.println("\n" + trees);
		*/

		//Part 2
		int r1d1 = 0, r3d1 = 0, r5d1 = 0, r7d1 = 0, r1d2 = 0;
		int j = 0;
		//right 1, down 1
		for (int i = 0; i < map.length; i++) {
			if (map[i][j] == '#')
				r1d1++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 1) % map[i].length; //Loop back around to emulate tiling
		}
		j = 0;
		//right 3 and down 1
		for (int i = 0; i < map.length; i++) {
			if (map[i][j] == '#')
				r3d1++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 3) % map[i].length; //Loop back around to emulate tiling
		}
		j = 0;
		//right 5 and down 1
		for (int i = 0; i < map.length; i++) {
			if (map[i][j] == '#')
				r5d1++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 5) % map[i].length; //Loop back around to emulate tiling
		}
		j = 0;
		//right 7 and down 1
		for (int i = 0; i < map.length; i++) {
			if (map[i][j] == '#')
				r7d1++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 7) % map[i].length; //Loop back around to emulate tiling
		}
		j = 0;
		//right 1 and down 2
		for (int i = 0; i < map.length; i+=2) {
			if (map[i][j] == '#')
				r1d2++;
			//System.out.println("Row " + (i+1) + " of " + map.length + "\t Column " + j);
			
			j = (j + 1) % map[i].length; //Loop back around to emulate tiling
		}

		System.out.println("R1D1: " + r1d1  + "\tR3D1: " + r3d1 + "\tR5D1: " + r5d1 + "\tR7D1: " + r7d1 + "\tR1D2: " + r1d2);
		System.out.println("Key: " + 1L * r1d1 * r3d1 * r5d1 * r7d1 * r1d2);
		//Key is greater than max int so need to convert to long
  }
}