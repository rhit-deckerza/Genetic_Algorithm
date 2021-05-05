import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ChromosomeMaker {
	private Scanner scanner;
	private FileReader file;

	public int[][] fileToChromosomeData(String filename) throws FileNotFoundException {
		Scanner scanner;
		FileReader file = new FileReader(filename);
		scanner = new Scanner(file);
		int lineNum = 0;
		String[] allInts = new String[0];
		ArrayList<String[]> allLines = new ArrayList<String[]>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			allInts = line.split(",");
			allLines.add(allInts);
		}
		int[][] chromosomeData = new int[allLines.size()][allInts.length];
		for (int i = 0; i < chromosomeData.length; i++) {
			for (int j = 0; j < chromosomeData[i].length; j++) {
				chromosomeData[i][j] = Integer.parseInt(allLines.get(i)[j]);
			}
		}
		return chromosomeData;
	}
}
