package glazer.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class Minesweeper extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ROWS = 20;
	private final int COLS = 20;
	private JToggleButton cells[][];
	private JPanel board;
	private boolean lost;
	private final int BOMBS = 100;
	private ImageIcon pic;
	private Set<JToggleButton> bombs;
	private Map<JToggleButton, Integer> neighbors;
	private Stack<JToggleButton> stack;
	private Map<JToggleButton, Integer> row;
	private Map<JToggleButton, Integer> col;
	private Set<JToggleButton> visited;
	private ImageIcon flag;
	private Set<JToggleButton> flagged;
	private JLabel numBombs;
	private int hiddenBombs;
	private JPanel topPanel;
	private JButton restart;
	private ImageIcon smile;

	public Minesweeper() {
		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		board = new JPanel();
		numBombs = new JLabel();
		topPanel = new JPanel();
		restart = new JButton();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(numBombs, BorderLayout.WEST);
		lost=false;
		board.setLayout(new GridLayout(ROWS, COLS));
		cells = new JToggleButton[COLS][ROWS];
		visited = new HashSet<JToggleButton>();
		flagged = new HashSet<JToggleButton>();
		Random random = new Random();
		row = new HashMap<JToggleButton, Integer>();
		col = new HashMap<JToggleButton, Integer>();
		stack = new Stack<JToggleButton>();
		neighbors = new HashMap<JToggleButton, Integer>();
		try {
			smile = new ImageIcon(
					new URL(
							"http://www.onlinespiele-sammlung.de/minesweeper/minesweepergames/thonky/smile.gif"));
			pic = new ImageIcon(
					new URL(
							"https://lh4.ggpht.com/dnO3r4ybzBi3V8Pnq26FrQRuptlBDC26IC4GZlKGt14WHKKFkBr3Zfb_pNXdpjGxC-s=w300	"));
			flag = new ImageIcon(new URL(
					"http://www.rw-designer.com/icon-view/3079.png"));
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		topPanel.add(restart, BorderLayout.CENTER);
		restart.setSize(20, 20);
		restart.setIcon(new ImageIcon(smile.getImage().getScaledInstance(
				restart.getWidth(), restart.getHeight(), Image.SCALE_SMOOTH)));
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		};
		restart.addActionListener(listener);
		bombs = new HashSet<JToggleButton>();
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				cells[i][j] = new JToggleButton();
				cells[i][j].setEnabled(true);
				int num = random.nextInt(400);

				cells[i][j].addMouseListener(new MouseAdapter() {
					
					@Override
					public void mousePressed(MouseEvent e) {

						Object o = e.getSource();
						JToggleButton button;

						if (o instanceof JToggleButton) {
							button = (JToggleButton) o;
							  
							 int both = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK;
							   // int offmask =InputEvent.BUTTON2_DOWN_MASK;
							    if ((e.getModifiersEx() & (both)) == both) {
							        System.out.println("both"+ e.getX()+" y "+ e.getY());
							    }
						
							if (SwingUtilities.isLeftMouseButton(e)) {
								if (!flagged.contains(button)) {
									if (bombs.contains(button)) {
										button.setIcon(new ImageIcon(pic
												.getImage().getScaledInstance(
														button.getWidth(),
														button.getHeight(),
														Image.SCALE_SMOOTH)));
										lost=true;
										//endGame();
										
									} else if (neighbors.get(button) == 0) {
										button.setIcon(null);
										flagged.remove(button);
										button.setSelected(true);
										button.setBackground(Color.WHITE);
										button.setForeground(Color.WHITE);
										button.setVisible(true);
										findCell(button);
									} else {
										button.setIcon(null);

										setText(button, neighbors.get(button));
										button.setSelected(false);
										button.setVisible(true);
									}
									visited.add(button);
								}
							} else if (SwingUtilities.isRightMouseButton(e)) {
								if (!visited.contains(button)) {
									if (flagged.contains(button)) {
										button.setIcon(null);
										flagged.remove(button);
										button.setSelected(false);
										hiddenBombs++;
										numBombs.setText(String
												.valueOf(hiddenBombs));
									} else {
										flagged.add(button);
										button.setSelected(true);
										button.setIcon(new ImageIcon(flag
												.getImage().getScaledInstance(
														button.getWidth(),
														button.getHeight(),
														Image.SCALE_SMOOTH)));
										hiddenBombs--;
										numBombs.setText(String
												.valueOf(hiddenBombs));
									}

								}
							} 
							
							else   if ((e.getModifiersEx() & (both)) == both) {
						        // do your thing
						        System.out.println("Both down");
						    	System.out.println("Both pressed");
								int flagNeighbors = getNumFlagNeighbors(button);
								if (visited.contains(button)
										&& neighbors.containsKey(button)) {
									if (neighbors.get(button) > 0
											&& flagNeighbors == neighbors
													.get(button)) {
										exposeNeighbors(button);
									}
								}
							}   }
								    
				
					}});

				if (num < 100) {
					hiddenBombs++;
					bombs.add(cells[i][j]);
				}
				board.add(cells[i][j]);
			}
			numBombs.setText(String.valueOf(hiddenBombs));
			container.add(topPanel, BorderLayout.NORTH);
			container.add(board, BorderLayout.CENTER);
		}

		for (int r = 0; r < ROWS; r++) {
			for (int j = 0; j < COLS; j++) {
				if (!isBomb(r, j)) {
					int num = getNumBombNeighbors(r, j);
					if (num == 0) {

						neighbors.put(cells[r][j], 0);
						row.put(cells[r][j], r);
						col.put(cells[r][j], j);
					} else {

						neighbors.put(cells[r][j], num);
						row.put(cells[r][j], r);
						col.put(cells[r][j], j);
					}
				}
			}
		}

	}

	public int getNumNeighbors(JToggleButton button) {

		return neighbors.get(button);
	}

	public void exposeNeighbors(JToggleButton button) {
		int r = row.get(button);
		int c = col.get(button);
		try {
			for (int i = r - 1; i <= r + 1; i++) {
				for (int j = c - 1; j <= c + 1; j++) {
					JToggleButton test = cells[i][j];
					if (!visited.contains(test) && neighbors.containsKey(test)) {
						visited.add(test);
						if (neighbors.get(test) == 0) {
							test.setBackground(Color.WHITE);
							findCell(test);
						} else {
							test.setIcon(null);

							test.setSelected(false);
							test.setVisible(true);
							setText(test, neighbors.get(test));
						}
					} else if (bombs.contains(test)) {
						test.setIcon(new ImageIcon(pic.getImage()
								.getScaledInstance(button.getWidth(),
										button.getHeight(), Image.SCALE_SMOOTH)));
						lost = true;
						endGame();
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void endGame() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j].setEnabled(false);
				cells[i][j].setVisible(true);
			}
		}
	}

	public int getNumBombNeighbors(int i, int j) {
		int numBomb = 0;
		if (isBomb(i - 1, j - 1)) {
			numBomb++;
		}
		if (isBomb(i - 1, j)) {
			numBomb++;
		}
		if (isBomb(i - 1, j + 1)) {
			numBomb++;
		}
		if (isBomb(i, j - 1)) {
			numBomb++;
		}
		if (isBomb(i, j + 1)) {
			numBomb++;
		}
		if (isBomb(i + 1, j - 1)) {
			numBomb++;
		}
		if (isBomb(i + 1, j)) {
			numBomb++;
		}
		if (isBomb(i + 1, j + 1)) {
			numBomb++;
		}
		return numBomb;
	}

	public void findCell(JToggleButton button) {
		int r = row.get(button);
		int c = col.get(button);
		button.setBackground(Color.WHITE);
		button.setVisible(true);
		findWhiteNeighbors(r, c);
	}

	public void findWhiteNeighbors(int i, int j) {
		stack.push(cells[i][j]);
		cells[i][j].setVisible(true);
		visited.add(cells[i][j]);
		JToggleButton current;
		while (!stack.isEmpty()) {
			boolean change = false;
			do {

				change = false;
				current = stack.peek();
				while (hasRight()) {
					change = true;
				}
				while (hasUp()) {
					change = true;
				}
				while (hasLeft()) {
					change = true;
				}
				while (hasDown()) {
					change = true;
				}
				while (hasUpRight()) {
					change = true;
				}
				while (hasUpLeft()) {
					change = true;
				}
				while (hasDownRight()) {
					change = true;
				}
				while (hasDownLeft()) {
					change = true;
				}

			} while (change);
			current.setVisible(true);
			stack.pop();
		}
	}

	public int getNumFlagNeighbors(JToggleButton button) {
		int i = row.get(button);
		int j = col.get(button);
		int numFlag = 0;
		if (isFlag(i - 1, j - 1)) {
			numFlag++;
		}
		if (isFlag(i - 1, j)) {
			numFlag++;
		}
		if (isFlag(i - 1, j + 1)) {
			numFlag++;
		}
		if (isFlag(i, j - 1)) {
			numFlag++;
		}
		if (isFlag(i, j + 1)) {
			numFlag++;
		}
		if (isFlag(i + 1, j - 1)) {
			numFlag++;
		}
		if (isFlag(i + 1, j)) {
			numFlag++;
		}
		if (isFlag(i + 1, j + 1)) {
			numFlag++;
		}

		return numFlag;
	}

	public boolean isFlag(int i, int j) {
		return flagged.contains(cells[i][j]);
	}

	public boolean hasRight() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c + 1 < COLS) {
			JToggleButton button = cells[r][c + 1];
			System.out.println("Checking button: " + r + " " + (c + 1));
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {
					setText(button, getNumBombNeighbors(r, c + 1));
				}
			}

		}
		return false;
	}

	public boolean hasUpRight() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c + 1 < COLS && r - 1 >= 0) {
			JToggleButton button = cells[r - 1][c + 1];
			System.out.println("Checking button: " + (r - 1) + " " + (c + 1));
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {
					setText(button, getNumBombNeighbors(r - 1, c + 1));
				}
			}

		}
		return false;
	}

	public boolean hasDownRight() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c + 1 < COLS && r + 1 < ROWS) {
			JToggleButton button = cells[r + 1][c + 1];
			System.out.println("Checking button: " + r + 1 + " " + c + 1);
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {
					setText(button, getNumBombNeighbors(r + 1, c + 1));
				}
			}

		}
		return false;
	}

	public boolean hasLeft() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c - 1 >= 0) {
			JToggleButton button = cells[r][c - 1];
			System.out.println("Checking button: " + r + " " + (c - 1));
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					button.setVisible(true);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {

					setText(button, getNumBombNeighbors(r, c - 1));
				}
			}
		}
		return false;
	}

	public boolean hasUpLeft() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c - 1 >= 0 && r - 1 >= 0) {
			JToggleButton button = cells[r - 1][c - 1];
			System.out.println("Checking button: " + (r - 1) + " " + (c - 1));
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					button.setVisible(true);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {

					setText(button, getNumBombNeighbors(r - 1, c - 1));
				}
			}
		}
		return false;
	}

	public boolean hasDownLeft() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c - 1 >= 0 && r + 1 < ROWS) {
			JToggleButton button = cells[r + 1][c - 1];
			System.out.println("Checking button: " + (r + 1) + " " + (c - 1));
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					button.setVisible(true);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {

					setText(button, getNumBombNeighbors(r + 1, c - 1));
				}
			}
		}
		return false;
	}

	public boolean hasUp() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (r - 1 >= 0) {
			JToggleButton button = cells[r - 1][c];
			System.out.println("Checking button: " + (r - 1) + " " + c);
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {

					setText(button, getNumBombNeighbors(r - 1, c));
				}
			}
		}
		return false;
	}

	public boolean hasDown() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (r + 1 < ROWS) {
			JToggleButton button = cells[r + 1][c];
			System.out.println("Checking button: " + (r + 1) + " " + c);
			if (!flagged.contains(button)) {
				if (neighbors.get(button).equals(0) & !visited.contains(button)) {
					button.setBackground(Color.WHITE);
					visited.add(button);
					stack.push(button);
					return true;
				} else if (!visited.contains(button)) {
					setText(button, getNumBombNeighbors(r + 1, c));

				}
			}
		}
		return false;
	}

	public boolean isBomb(int i, int j) {
		try {
			return bombs.contains(cells[i][j]);
		} catch (Exception e) {
			return false;
		}
	}

	public void setText(JToggleButton button, int num) {
		visited.add(button);
		button.setSelected(true);

		button.setText(String.valueOf(num));
	}

	public void setUpGame() {

	}

	public static void main(String[] args) {
		Minesweeper sweep = new Minesweeper();
		sweep.setVisible(true);

	}

}
