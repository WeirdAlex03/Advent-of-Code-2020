import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) {
		//[passport][field]
		/*
		0 byr (Birth Year)
		1 iyr (Issue Year)
		2 eyr (Expiration Year)
		3 hgt (Height)
		4 hcl (Hair Color)
		5 ecl (Eye Color)
		6 pid (Passport ID)
		7 cid (Country ID)
		*/
		String[][] passports = new String[259][8];	//Main: 259, Examples: 4
		
		try {
			Scanner stan = new Scanner(new File("passports.txt"));
			String info = "", line;
			int currentPassport = 0;

			//Fill data into array
			while (stan.hasNext()) {
				//Load all info for each passport into one string
				line = stan.nextLine();
				if (!line.equals(""))
					info = new String(info + " " + line);
				else {
					//Process data into array
					
					passports[currentPassport][0] = readInfo(info, "byr");
					passports[currentPassport][1] = readInfo(info, "iyr");
					passports[currentPassport][2] = readInfo(info, "eyr");
					passports[currentPassport][3] = readInfo(info, "hgt");
					passports[currentPassport][4] = readInfo(info, "hcl");
					passports[currentPassport][5] = readInfo(info, "ecl");
					passports[currentPassport][6] = readInfo(info, "pid");
					passports[currentPassport][7] = readInfo(info, "cid");
					
					currentPassport++;
					info = new String();
				}
			}
			passports[currentPassport][0] = readInfo(info, "byr");
			passports[currentPassport][1] = readInfo(info, "iyr");
			passports[currentPassport][2] = readInfo(info, "eyr");
			passports[currentPassport][3] = readInfo(info, "hgt");
			passports[currentPassport][4] = readInfo(info, "hcl");
			passports[currentPassport][5] = readInfo(info, "ecl");
			passports[currentPassport][6] = readInfo(info, "pid");
			passports[currentPassport][7] = readInfo(info, "cid");
			stan.close();

		} catch (FileNotFoundException e) {
			System.err.println("ERR: File not found!");
		}

		//Part 1
		System.out.println("--- Part 1 ---");

		int totalValid = 0;
		boolean isValid;
		for (int i = 0; i < passports.length; i++) {
			isValid = true;
			for (int j = 0; j < passports[i].length-1; j++) {
				//(Skip passports[][7] because cid is optional)
				if (passports[i][j] == null) {
					isValid = false;
				}
			}
			
			if (isValid) {
				totalValid++;
			}
		}
		System.out.println("Total valid: " + totalValid);


		System.out.println("\n\n");

		//Part 2
		System.out.println("--- Part 2 ---");
		totalValid = 0;
		for (int i = 0; i < passports.length; i++) {
			//System.out.print(i+"\t");
			isValid = true;
			
			for (int j = 0; j < passports[i].length-1; j++) {
				//(Skip passports[][7] because cid is optional)
				if (passports[i][j] == null) {
					//System.out.print("failed (missing data) ");
					isValid = false;
				}
			}
			
			if (isValid) {
				//byr: at least 1920, at most 2002
				if (Integer.parseInt(passports[i][0]) < 1920 || Integer.parseInt(passports[i][0]) > 2002) {
					isValid = false;
					//System.out.print("falied 0 (byr) ");
				}
				//iyr: at least 2010, at most 2020
				if (Integer.parseInt(passports[i][1]) < 2010 || Integer.parseInt(passports[i][1]) > 2020) {
					isValid = false;
					//System.out.print("falied 1 (iyr) ");
				}
				//eyr: at least 2020, at most 2030
				if (Integer.parseInt(passports[i][2]) < 2020 || Integer.parseInt(passports[i][2]) > 2030) {
					isValid = false;
					//System.out.print("falied 2 (eyr) ");
				}
				//hgt: 150-193cm or 59-76in
				if (passports[i][3].contains("cm") && passports[i][3].length() == 5) {
					if (Integer.parseInt(passports[i][3].substring(0, 3)) < 150 || Integer.parseInt(passports[i][3].substring(0, 3)) > 193) {
						isValid = false;
						//System.out.print("falied 3 (cm) ");
					}
				} else if (passports[i][3].contains("in") && passports[i][3].length() == 4) {
					if (Integer.parseInt(passports[i][3].substring(0, 2)) < 59 || Integer.parseInt(passports[i][3].substring(0, 2)) > 76) {
						isValid = false;
						//System.out.print("falied 3 (in) ");
					}
				} else {
					isValid = false;
					//System.out.print("falied 3 (short/no unit) ");
				}
				//hcl: hex color code
				if (passports[i][4].length() == 7) {
					if (passports[i][4].charAt(0)=='#') {
						for (int j = 1; j < 7; j++) {
							if (passports[i][4].charAt(j) == 'a' || passports[i][4].charAt(j) == 'b' || passports[i][4].charAt(j) == 'c' || passports[i][4].charAt(j) == 'd' || passports[i][4].charAt(j) == 'e' || passports[i][4].charAt(j) == 'f' ) {
							}  else {
								try {
									if (Integer.parseInt(passports[i][4].substring(j,j+1))>=0) {}
								} catch (NumberFormatException e) {
									isValid = false;
									//System.out.print("failed 4 (NaN) ");
								}
							}
						}
					} else {
						//System.out.print("failed 4 (no #) ");
						isValid = false;
					}
				} else {
				//System.out.print("failed 4 (short) ");
				isValid = false;
				}
				//ecl: amb, blu, brn, gry, grn, hzl, oth
				if (passports[i][5].equals("amb") || passports[i][5].equals("blu") || passports[i][5].equals("brn") || passports[i][5].equals("gry") || passports[i][5].equals("grn") || passports[i][5].equals("hzl") || passports[i][5].equals("oth")) {
				} else {
					isValid = false;
					//System.out.print("falied 5 (ecl) ");
				}
				//pid: nine-digit number, incl 0s
				if (passports[i][6].length() != 9) {
					isValid = false;
					//System.out.print("falied 6 (pid) ");
				}
			}

			if (isValid) {
				//System.out.print("passed");
				totalValid++;
			}
			//System.out.println();//"\t\t"+isValid+"\t"+totalValid);
		}
		System.out.println("Total valid within parameters: " + totalValid);
	}
	
	/* Returns the value of the passport field, null if not present
	 * 
	 * line: The String to look for the data in
	 * field: The String of the name of the field to find
	 */
	static String readInfo(String line, String field) {
		if (line.contains(field)) {
			//Field is present
			if (line.indexOf(" ",line.indexOf(field)) == -1) {
				//Field is at the end of the line
				return line.substring(line.indexOf(field)+field.length()+1,line.length());
			}
			else {
				//Field not at the end (has more data after it)
				return line.substring(line.indexOf(field)+field.length()+1,line.indexOf(" ",line.indexOf(field)));
			}
		}
		else {
			//Field is not present
			return null;
		}
	}  
}