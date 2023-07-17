package ie.atu.sw;

import java.io.*;

public class Outputter {
	
	public int newSize;

	public Object[][] outTable;
	
	//To remove null entries in table, number of not null rows are counted.
	
	public void getNewTableSize(Object[][] table, int newSize) {
		int counter = 0;
		for (int i = 0; i <= table.length - 1; i++) {
			if (table[i][0] != null) {
				counter++;
			}
		}
		this.newSize = counter;
	}

	//A new table is created, using the new table size of only filled rows.
	
	public void createNewTable(Object[][] table, int newSize) {
		Object[][] newTable = new Object[newSize][2];
		int k = 0;
		for (int j = 0; j <= table.length - 1; j++) {
			if (table[j][0] != null) {
				newTable[k] = table[j];
				k++;
			}
		}
		this.outTable = newTable;
	}

	//Updated table is saved to file, specified by user.
	
	public void save(Object[][] outTable, String file) throws Exception {
		PrintWriter pw = new PrintWriter(new File(file));

		for (int row = 0; row < outTable.length; row++) {
			pw.write(outTable[row][0] + "," + outTable[row][1] + "\n");
		}

		pw.close();
	}
}
