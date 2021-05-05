
import java.awt.Color;
import java.awt.Graphics2D;

public class Chromosome {
	private static final int WIDTH = 50;

	private Color green = Color.PINK;
	private Color black = Color.YELLOW;
	private int num = 0;
	private int[][] dna;
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;

	public Chromosome(int[][] dna) {
		this.dna = dna;
	}

	public void drawOn(Graphics2D g) {
		for (int i = 0; i < dna.length; i++) {
			y1 = WIDTH * i;
			y2 = y1 + WIDTH;
			for (int k = 0; k < dna[i].length; k++) {
				x1 = WIDTH * k;
				x2 = x1 + WIDTH;
				if (dna[i][k] == 0) {
					g.setColor(green);
				} else {
					g.setColor(black);
				}
//				System.out.println(x1 + "," + x2 + "," + y1 + "," + y2);
				g.drawRect(x1, y1, x2, y2);
				g.fillRect(x1, y1, x2, y2);
			}
		}
		y1 = 0;
		y2 = 0;
		x1 = 0;
		x2 = 0;
		for (int i = 0; i < dna.length; i++) {
			y1 = WIDTH * i;
			y2 = y1 + WIDTH;
			for (int k = 0; k < dna[i].length; k++) {
				x1 = WIDTH * k;
				x2 = x1 + WIDTH;
				String numString = "" + i + k;
				g.setColor(Color.BLACK);
				g.drawString(numString, x1 + 10, y1 + WIDTH / 3);
			}
		}
	}
//		x1 = 0;
//		x2 = 0;
//		for(int i = 0; i < dna.length; i++) {
//			x1 += WIDTH;
//			x2 += WIDTH;
//			y1 = WIDTH;
//			y2 = WIDTH;
//			for(int k = 0; k < dna[i].length; k++) {
//				drawNum(g, num);
//				num++;
//				y1+=WIDTH;
//				y2+=WIDTH;
//			}
//		}
//	}
//	private void drawRectangle(Graphics2D g, int color) {
//		// TODO Auto-generated method stub
//		if(color == 0) {
//			g.setColor(green);
//		}
//		else {
//			g.setColor(black);
//		}
//		g.drawRect(x1, y1, x2, y2);
//		g.fillRect(x1, y1, x2, y2);
//		
//	}
//	
//	private void drawNum(Graphics2D g, int num2) {
//		// TODO Auto-generated method stub
//		String numString = "" + num2;
//		g.setColor(Color.BLACK);
//		g.drawString(numString, y1+10, x1+WIDTH/3);
//	}

}
