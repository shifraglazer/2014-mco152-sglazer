package glazer.gui.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameOfLife extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final int ROWS = 20;
	private final int COLS = 20;
	private JButton cells[][] = new JButton[COLS][ROWS];

	public GameOfLife() {
		this.setSize(800, 600);
		this.setTitle("Game of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		BorderLayout border = new BorderLayout();
		Container container = getContentPane();
		Container center = new Container();
		GridLayout layout = new GridLayout(ROWS, COLS);
		container.setLayout(border);
		center.setLayout(layout);
		Container west = new Container();
		JButton generation = new JButton();

		ActionListener nextGeneration = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				nextGeneration();
			}

		};
		/*
		 * final ActionListener stopPlay = new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent event) {
		 * continuePlay = false; }
		 * 
		 * }; ActionListener playGeneration = new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent event) {
		 * stop.addActionListener(stopPlay); while (continuePlay) {
		 * nextGeneration(); } try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } };
		 */

		generation.addActionListener(nextGeneration);
		generation.setText("Next Generation");
		generation.setVisible(true);
		BoxLayout box = new BoxLayout(west, BoxLayout.Y_AXIS);
		west.setLayout(box);
		west.add(generation);
		/*
		 * play.setText("Play"); play.setVisible(true);
		 * 
		 * stop.setText("Stop"); stop.setVisible(true);
		 * 
		 * west.add(play); west.add(stop);
		 */
		container.add(center, BorderLayout.CENTER);
		container.add(west, BorderLayout.WEST);
		west.setVisible(true);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button.getBackground() == Color.BLACK) {
					button.setBackground(Color.GREEN);
				} else {
					button.setBackground(Color.BLACK);
				}
			}

		};

		Random random = new Random();
		for (int j = 0; j < COLS; j++) {
			for (int i = 0; i < ROWS; i++) {
				final JButton button = new JButton();
				cells[j][i] = button;
				center.add(button);
				button.addActionListener(listener);
				int n = random.nextInt();
				if (n < 30) {
					button.setBackground(Color.GREEN);
				} else {
					button.setBackground(Color.BLACK);
				}

			}
		}

	}

	public void nextGeneration() {
		JButton[][] buttons = new JButton[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				JButton button = new JButton();
				button.setBackground(cells[i][j].getBackground());
				buttons[i][j] = button;
			}
		}

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				int neighbors = getNumAliveNeighbors(i, j);
				// System.out.println(i + " " + j + " " + neighbors);

				switch (neighbors) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					buttons[i][j].setBackground(Color.BLACK);
					break;
				case 2:
					break;
				case 3:
					buttons[i][j].setBackground(Color.GREEN);
					break;
				}
			}
		}

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j].setBackground(buttons[i][j].getBackground());
			}
		}
	}

	public int getNumAliveNeighbors(int i, int j) {
		int numAlive = 0;
		if (isAlive(i - 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i - 1, j)) {
			numAlive++;
		}
		if (isAlive(i - 1, j + 1)) {
			numAlive++;
		}
		if (isAlive(i, j - 1)) {
			numAlive++;
		}
		if (isAlive(i, j + 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j - 1)) {
			numAlive++;
		}
		if (isAlive(i + 1, j)) {
			numAlive++;
		}
		if (isAlive(i + 1, j + 1)) {
			numAlive++;
		}
		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		try {
			return cells[i][j].getBackground() == Color.GREEN;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String args[]) {
		GameOfLife frame = new GameOfLife();
		frame.setVisible(true);
	}

}
