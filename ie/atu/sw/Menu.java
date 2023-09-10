package ie.atu.sw;

import java.util.Scanner;

public class Menu {

	// Instance variables for holding user input.

	private static Scanner s = new Scanner(System.in);

	private static boolean keepRunning = true;

	private static String directory;

	private static int n;

	private static String outLoc;

	private static int type;

	// Application Header

	public void showHeader() {
		System.out.println("************************************************************");
		System.out.println("*      ATU - Dept. Computer Science & Applied Physics      *");
		System.out.println("*                                                          *");
		System.out.println("*                  N-Gram Frequency Builder                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
	}

	// Method containing menu options and getter methods for user input values.

	public static void showMenu() throws Exception {
		System.out.println();
		System.out.println("(1) Specify Text File Directory");
		System.out.println("(2) Specify n-Gram Size");
		System.out.println("(3) Specify Output File");
		System.out.println("(4) Specify n-Gram Type : Chunky (1) or Tiled (2)");
		System.out.println("(5) Build n-Grams ");
		System.out.println("(6) Quit");
		System.out.println();

		// Output a menu of options and solicit text from the user
		System.out.print("Select Option [1-6]> \n");
		System.out.println();

		int choice = s.nextInt();

		while (keepRunning) {
			switch (choice) {
				case 1 -> setTextDirectory();
				case 2 -> setSize();
				case 3 -> setOutputFile();
				case 4 -> setNgramType();
				case 5 -> {
					Builder b = new Builder();
					b.build(directory, choice, outLoc, choice);
				}
				case 6 -> shutDown();
			}
		}
	}

	public static void setTextDirectory() throws Exception {
		s.nextLine();
		System.out.println("Please specify directory location:");
		directory = s.nextLine();
		System.out.println("\n Thank you, directory specified was: " + directory);
		showMenu();
	}

	public static void setSize() throws Exception {
		s.nextLine();
		System.out.println("Please specify an n-gram size:");
		n = s.nextInt();
		System.out.println("\n Thank you. N-Gram size for this build = " + n);
		showMenu();
	}

	public static void setOutputFile() throws Exception {
		s.nextLine();
		System.out.println("Please specify an output location:");
		outLoc = s.nextLine();
		System.out.println("\n Thank you, specified output file was: " + outLoc);
		showMenu();
	}

	public static void setNgramType() throws Exception {
		s.nextLine();
		System.out.println("Please specify n-Gram type: Chunky (1) or Tiled (2) \n");
		type = s.nextInt();
		if (type == 1) {
			System.out.println("Thank you, specified n-Gram type is Chunky \n");
		} else if (type == 2) {
			System.out.println("Thank you, specified n-Gram type is Tiled \n");
		} else {
			System.out.println("[ERROR] Invalid input. Please select (1) for Chunky or (2) for Tiled");
		}
		showMenu();
	}

	public static void shutDown() {
		System.out.println("Shutting down...");
		keepRunning = false;
	}
}
