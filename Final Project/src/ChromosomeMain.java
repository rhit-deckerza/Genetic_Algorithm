import java.io.FileNotFoundException;

public class ChromosomeMain {
	public static void main(String[] args) throws FileNotFoundException {
		ChromosomeMaker chromosomeMaker = new ChromosomeMaker();
		new ChromosomeViewer(chromosomeMaker, "C:\\Users\\deckerza\\OneDrive\\Final Project\\src\\Smile.txt");
	}
}
