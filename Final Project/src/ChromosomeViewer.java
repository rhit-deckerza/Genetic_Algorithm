import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChromosomeViewer {
	private JLabel label = new JLabel();
	private String filename;
	private int[][] chromosomeData;
	private ChromosomeMaker chromosomeMaker;
	private JFrame frame;
	private int mutationRate;
	private BlockComponent component;
	private int loadCount = 0;

	public String getFilename() {
		return this.filename;
	}

	public int[][] getChromosomeData() {
		return this.chromosomeData;
	}

	public boolean mutate() {
		int count = 0;
		double percentage = this.mutationRate / ((double) (this.chromosomeData.length * this.chromosomeData[0].length));
		System.out.println(percentage);
		for (int i = 0; i < this.chromosomeData.length; i++) {
			for (int j = 0; j < this.chromosomeData[i].length; j++) {
				double random = Math.random();
				System.out.println(random);
				if (random <= percentage) {
					count++;
					if (this.chromosomeData[i][j] == 0) {
						this.chromosomeData[i][j] = 1;
					} else {
						this.chromosomeData[i][j] = 0;
					}
				}
			}
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateLabel(String filename) {
		this.filename = filename;
		this.label.setText(filename);
	}

	public void updateChromosomeDataFile(String filename) {
		try {
			this.chromosomeData = this.chromosomeMaker.fileToChromosomeData(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found");
		}

	}

	public void changeSingleBox(int x, int y) {
		if (this.chromosomeData[x][y] == 0) {
			this.chromosomeData[x][y] = 1;
		} else {
			this.chromosomeData[x][y] = 0;
		}
	}

	public void changeMutationRate(int mutationRate) {
		try {
			this.mutationRate = mutationRate;
		} catch (NumberFormatException e) {
			System.out.println("Not a number");
		}

	}

	public void updateChromosomePicture() {

		if (loadCount == 0) {
			this.component = new BlockComponent(this.chromosomeData);
			this.frame.add(component);
			this.frame.repaint();
			this.frame.setPreferredSize(
					new Dimension(this.chromosomeData[0].length * 50 + 20, this.chromosomeData.length * 50 + 110));
			System.out.println("first load successful");
		} else {
			this.component.chromosomeData = this.chromosomeData;
			this.component.repaint();
			this.frame.setSize(
					new Dimension(this.chromosomeData[0].length * 50 + 20, this.chromosomeData.length * 50 + 110));
		}
		loadCount++;

		System.out.println("pog");
	}

	public ChromosomeViewer(ChromosomeMaker chromosomeMaker, String filename) {
		JFrame frame = new JFrame();
		this.frame = frame;
		this.frame.setTitle("Genetic Algorithm");
		JPanel panel = new JPanel(new GridLayout(2, 2));

		this.filename = filename;
		this.chromosomeMaker = chromosomeMaker;
		updateLabel(this.filename);
		updateChromosomeDataFile(this.filename);

		frame.addMouseListener(new ChromosomeListener());

		this.frame.add(label, BorderLayout.NORTH);
		this.frame.add(panel, BorderLayout.SOUTH);
		updateChromosomePicture();

		JLabel rateLable = new JLabel("Enter Mutation Rate");
		JButton load = new JButton("Load");
		JButton save = new JButton("Save");
		JButton mutate = new JButton("Mutate");
		JTextField rate = new JTextField("Mutation Rate");
		panel.add(mutate);
		panel.add(rateLable);
		panel.add(rate);
		panel.add(load);
		panel.add(save);
		mutate.addActionListener(new ChromosomeListener(mutate));
		load.addActionListener(new ChromosomeListener(load));
		rate.addActionListener(new ChromosomeListener(rate));
		save.addActionListener(new ChromosomeListener(save));

		this.frame
				.setPreferredSize(new Dimension(chromosomeData[0].length * 50 + 20, chromosomeData.length * 50 + 110));
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
	}

	public class ChromosomeListener implements ActionListener, MouseListener {
		private BlockComponent component;
		private JButton button;
		private JFileChooser fileChooser;
		private JTextField text;

		public ChromosomeListener() {
		}

		public ChromosomeListener(JButton button) {
			this.button = button;
		}

		public ChromosomeListener(JFileChooser fileChooser) {
			this.fileChooser = fileChooser;
		}

		public ChromosomeListener(JTextField text) {
			this.text = text;
		}

		public ChromosomeListener(BlockComponent component) {
			this.component = component;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String com = arg0.getActionCommand();
			if (com.equals("Load")) {
				JFileChooser fileChooser = new JFileChooser();
				File dir = new File("C:\\Users\\deckerza\\OneDrive\\Final Project\\src\\");
				fileChooser.setCurrentDirectory(dir);
				fileChooser.addActionListener(new ChromosomeListener(fileChooser));
				int result = fileChooser.showOpenDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					updateLabel(selectedFile.getAbsolutePath());
					updateChromosomeDataFile(selectedFile.getAbsolutePath());
					updateChromosomePicture();
				}
			}
			if (com.equals("Save")) {
				if (getFilename().contains("(mutated)")) {
					File file = new File(getFilename());
					try {
						FileWriter writer = new FileWriter(file);
						for (int i = 0; i < getChromosomeData().length; i++) {
							String line = "";
							for (int j = 0; j < getChromosomeData()[i].length; j++) {
								line += getChromosomeData()[i][j] + ",";
							}
							writer.write(line + "\n");
						}
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();

					}
				}
			}
			if (com.equals("Mutate")) {
				boolean changeLable = mutate();
				updateChromosomePicture();
				String temp = getFilename().substring(0, getFilename().length() - 4) + "(mutated).txt";
				if (!getFilename().contains("(mutated)") && changeLable) {
					updateLabel(temp);
				}
			} else {
				changeMutationRate(Integer.parseInt(com));
				System.out.println(com);
			}

		}

		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX() / 50;
			int y = (e.getY() - 52) / 50;
			System.out.println(e.getY() + "," + e.getX());
			System.out.println(x + "," + y);
			changeSingleBox(y, x);
			String temp = getFilename().substring(0, getFilename().length() - 4) + "(mutated).txt";
			if (!getFilename().contains("(mutated)")) {
				updateLabel(temp);
			}
			updateChromosomePicture();

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
