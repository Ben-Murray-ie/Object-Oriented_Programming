package ie.atu.sw;

import java.io.*;

public class FileParser {

	//Directory is broken into files and passed to parseFile
	
	public void parseDir(String dir, int n, int type) throws Exception {
		File f = new File(dir);
		File[] files = f.listFiles();
		for (File file : files) {
			parseFile(file, n, type);
		}
	}

	//Files are broken into lines, lines are broken into words and words are broken into n-grams, depending on n-gram type selected. N-grams are passed to addNgram method.
	
	public void parseFile(File file, int n, int type) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					int start = 0;
					int end = n;
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (type == 1) {
						// CHUNKY
						while (end <= word.length()) {
							String ngram = word.substring(start, end);
							addNGram(ngram);
							start += n;
							end += n;
						}
					} else if (type == 2) {
						// TILED
						while (end <= word.length()) {
							String ngram = word.substring(start, end);
							addNGram(ngram);
							start++;
							end++;
						}
					} else {
						System.out.println("[ERROR] Please select valid n-Gram type.");
						// Add showMenu here?
					}
				}
			}
			br.close();
		}
	}
	
	//N-grams are added to table and the number of each distinct n-gram is updated.
	
	public Object[][] table = new Object[(int) Math.pow(26.0, Menu.n)][2];

	public void addNGram(String ngram) {
		int index = ngram.hashCode() % table.length;

		long counter = 1;

		if (table[index][1] != null) {
			counter += (Long) table[index][1];
		}
		table[index][0] = ngram;
		table[index][1] = counter;
	}

}
