package ie.atu.sw;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileParser implements Runnable {

	private ConcurrentHashMap<String, Integer> table = new ConcurrentHashMap<>();
	private int n;
	private int type;
	private String dir;

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public ConcurrentHashMap<String, Integer> getTable() {
		return this.table;
	}

	public void setTable(ConcurrentHashMap<String, Integer> table) {
		this.table = table;
	}

	public int getN() {
		return this.n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void run(String dir, int n, int type) {
		setN(n);
		setType(type);
		setDir(dir);
		try {
			parseDir(this.getDir, this.getN(), this.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Directory is broken into files and passed to parseFile

	public void parseDir(String dir, int n, int type) throws Exception {
		File f = new File(dir);
		File[] files = f.listFiles();
		for (File file : files) {
			parseFile(file, n, type);
		}
	}

	// Files are broken into lines, lines are broken into words and words are broken
	// into n-grams, depending on n-gram type selected. N-grams are passed to
	// addNgram method.

	public void parseFile(File file) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					word = word.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
					if (type == 1) {
						parseChunky(word, n);
					} else if (type == 2) {
						parseTiled(word, n);
					} else {
						System.out.println("[ERROR] Please select valid n-Gram type.");
					}
				}
			}
			br.close();
		}
	}

	// N-grams are added to table and the number of each distinct n-gram is updated.

	private void addNGram(String ngram) {
		table.put(ngram, table.getOrDefault(ngram, 0) + 1);
	}

	private void parseChunky(String word, int n) {
		int start = 0;
		int end = n;
		while (end <= word.length()) {
			String ngram = word.substring(start, end);
			addNGram(ngram);
			start += n;
			end += n;
		}
	}

	private void parseTiled(String word, int n) {
		int start = 0;
		int end = n;
		while (end <= word.length()) {
			String ngram = word.substring(start, end);
			addNGram(ngram);
			start++;
			end++;
		}
	}

}
