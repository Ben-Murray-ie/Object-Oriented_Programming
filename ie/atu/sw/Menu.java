package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	
	//Instance variables for holding user input.

	private static Scanner s = new Scanner(System.in);

	private static boolean keepRunning = true;

	private static String directory;

	public static int n;

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

	//Method containing menu options and getter methods for user input values.
	
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
			case 1 -> getTextDirectory();
			case 2 -> getSize();
			case 3 -> getOutputFile();
			case 4 -> getNgramType();
			case 5 -> build(directory, n, outLoc, type);
			case 6 -> shutDown();
			}
		}
	}

	public static void getTextDirectory() throws Exception {
		s.nextLine();
		System.out.println("Please specify directory location:");
		directory = s.nextLine();
		System.out.println("\n Thank you, directory specified was: " + directory);
		showMenu();
	}

	public static void getSize() throws Exception {
		s.nextLine();
		System.out.println("Please specify an n-gram size:");
		n = s.nextInt();
		System.out.println("\n Thank you. N-Gram size for this build = " + n);
		showMenu();
	}

	public static void getOutputFile() throws Exception {
		s.nextLine();
		System.out.println("Please specify an output location:");
		outLoc = s.nextLine();
		System.out.println("\n Thank you, specified output file was: " + outLoc);
		showMenu();
	}

	public static void getNgramType() throws Exception {
		s.nextLine();
		System.out.println("Please specify n-Gram type: Chunky (1) or Tiled (2) \n");
		type = s.nextInt();
		// Maybe add if statement here to specify "chunky" or "tiled", rather than 1 or
		// 2.
		if (type == 1) {
			System.out.println("Thank you, specified n-Gram type is Chunky \n");
		} else if (type == 2) {
			System.out.println("Thank you, specified n-Gram type is Tiled \n");
		} else {
			System.out.println("[ERROR] Invalid input. Please select (1) for Chunky or (2) for Tiled");
		}
		showMenu();
	}

	public static void build(String directory, int n, String outLoc, int type) throws Exception {
		System.out.println("Building frequency table. Please wait.");
		long start = System.nanoTime();
		FileParser p = new FileParser();
		Outputter o = new Outputter();
		p.parseDir(directory, n, type);
		o.getNewTableSize(p.table, o.newSize);
		System.out.println("Final Table Size = " + o.newSize + " entries.");
		o.createNewTable(p.table, o.newSize);
		o.save(o.outTable, outLoc);
		System.out.println("Build complete.");
		long end = System.nanoTime();
		long total = end - start;
		double timeMillis = total / 1000000.0;
		System.out.println("Build Time = " + timeMillis);
		keepRunning = false;
	}

	public static void shutDown() {
		System.out.println("Shutting down...");
		keepRunning = false;
	}
}
