
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class BlockComponent extends JComponent {
	public int[][] chromosomeData;
	private int x;
	private int y;

	public BlockComponent(int[][] chromosomeData) {
		this.chromosomeData = chromosomeData;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics;
		Chromosome chromosome = new Chromosome(this.chromosomeData);
		chromosome.drawOn(graphics2);

		// position drawer
		graphics2.setColor(Color.BLACK);
		String text = "idk";
		graphics2.drawString(text, x, y);
	}

	public void printPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
