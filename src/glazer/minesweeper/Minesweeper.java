package glazer.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
	private final int BOMBS = 100;
	private ImageIcon pic;
	private Set<JToggleButton> bombs;
	private Map<JToggleButton, Integer> neighbors;
	private Stack<JToggleButton> stack;
	private Map<JToggleButton, Integer> row;
	private Map<JToggleButton, Integer> col;
	private Set<JToggleButton> visited;
	private ImageIcon flag;

	Minesweeper() {
		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		board = new JPanel();
		board.setLayout(new GridLayout(ROWS, COLS));
		cells = new JToggleButton[COLS][ROWS];
		visited=new HashSet<JToggleButton>();
		
		Random random = new Random();
		row=new HashMap<JToggleButton,Integer>();
		col=new HashMap<JToggleButton,Integer>();
		stack=new Stack<JToggleButton>();
		neighbors = new HashMap<JToggleButton, Integer>();
		try {
			pic = new ImageIcon(
					new URL(
							"https://lh4.ggpht.com/dnO3r4ybzBi3V8Pnq26FrQRuptlBDC26IC4GZlKGt14WHKKFkBr3Zfb_pNXdpjGxC-s=w300	"));
			flag=new ImageIcon(new URL("http://www.rw-designer.com/icon-view/3079.png"));
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		bombs = new HashSet<JToggleButton>();
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				cells[i][j] = new JToggleButton();
				//cells[i][j].setDisabledIcon(pic);
				cells[i][j].setSelectedIcon(flag);
				int num = random.nextInt(400);
				
				

				ActionListener bomb = new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {

						final JToggleButton  button = (JToggleButton) event.getSource();
						button.addMouseListener(new MouseAdapter(){
							
							@Override
						  public void mouseClicked(MouseEvent e){
							int b=e.getButton();
							  if (b==MouseEvent.BUTTON1) {
				                   button.setIcon(new ImageIcon(pic.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
							  }
							  else if(b==MouseEvent.BUTTON3){
								  button.setIcon(new ImageIcon(flag.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
							  }
							
				               }});
						
					}

				};
				
				if (num < 100) {

					cells[i][j].addActionListener(bomb);
					bombs.add(cells[i][j]);
				}
				board.add(cells[i][j]);
			}
			container.add(board, BorderLayout.CENTER);
		}

		ActionListener whitespace = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JToggleButton button = (JToggleButton) e.getSource();
				button.setBackground(Color.WHITE);
				button.addMouseListener(new MouseAdapter(){
					
					@Override
				  public void mouseClicked(MouseEvent e){
						int b=e.getButton();
					  if (b==MouseEvent.BUTTON1) {
							findCell(button);
					  }
					  else  if(b==MouseEvent.BUTTON3){
						  button.setIcon(new ImageIcon(flag.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
					  }
					
		               }});
				
			

			}

		};
		ActionListener number = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JToggleButton button = (JToggleButton) e.getSource();
	button.addMouseListener(new MouseAdapter(){
					
					@Override
				  public void mouseClicked(MouseEvent e){
						int b=e.getButton();
					  if (b==MouseEvent.BUTTON1) {
							button.setText(String.valueOf(getNumNeighbors(button)));
					  }
					  else if(b==MouseEvent.BUTTON3){
						
						  button.setIcon(new ImageIcon(flag.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
					  }
					
		               }});
			

			}

		};
		for (int r = 0; r < ROWS; r++) {
			for (int j = 0; j < COLS; j++) {
				if (!isBomb(r, j)) {
					int num = getNumBombNeighbors(r, j);
					if (num == 0) {
						cells[r][j].addActionListener(whitespace);
						neighbors.put(cells[r][j], 0);
						row.put(cells[r][j], r);
						col.put(cells[r][j],j);
					} else {
						cells[r][j].addActionListener(number);
						neighbors.put(cells[r][j], num);
						row.put(cells[r][j], r);
						col.put(cells[r][j],j);
					}
				}
			}
		}
		
		}
	public int getNumNeighbors(JToggleButton button) {

		return neighbors.get(button);
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

	private void findCell(JToggleButton button) {
		int r = row.get(button);
		int c = col.get(button);
		findWhiteNeighbors(r, c);
	}

	public void findWhiteNeighbors(int i, int j) {
		stack.push(cells[i][j]);
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
			} while (change);
			stack.pop();
		}
	}

	private boolean hasRight() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c + 1 < COLS) {
			JToggleButton button = cells[r][c + 1];
			System.out.println("Checking button: " + r + " " + c);
			if (neighbors.get(button).equals(0) & !visited.contains(button)) {
				button.setBackground(Color.WHITE);
				visited.add(button);
				stack.push(button);
				return true;
			} else if (!visited.contains(button)) {
				visited.add(button);
				button.setText(String.valueOf(getNumBombNeighbors(r, c + 1)));
			}

		}
		return false;
	}

	private boolean hasLeft() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (c - 1 >= 0) {
			JToggleButton button = cells[r][c - 1];
			System.out.println("Checking button: " + r + " " + c);
			if (neighbors.get(button).equals(0) & !visited.contains(button)) {
				button.setBackground(Color.WHITE);
				button.setVisible(true);
				visited.add(button);
				stack.push(button);
				return true;
			} else if (!visited.contains(button)) {
				visited.add(button);
				button.setText(String.valueOf(getNumBombNeighbors(r, c - 1)));
			}

		}
		return false;
	}

	private boolean hasUp() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (r - 1 >= 0) {
			JToggleButton button = cells[r - 1][c];
			System.out.println("Checking button: " + r + " " + c);
			if (neighbors.get(button).equals(0) & !visited.contains(button)) {
				button.setBackground(Color.WHITE);
				visited.add(button);
				stack.push(button);
				return true;
			} else if (!visited.contains(button)) {
				visited.add(button);
				button.setText(String.valueOf(getNumBombNeighbors(r - 1, c)));
			}

		}
		return false;
	}

	private boolean hasDown() {
		int r = row.get(stack.peek());
		int c = col.get(stack.peek());
		if (r + 1 < ROWS) {
			JToggleButton button = cells[r + 1][c];
			System.out.println("Checking button: " + r + " " + c);
			if (neighbors.get(button).equals(0) & !visited.contains(button)) {
				button.setBackground(Color.WHITE);
				visited.add(button);
				stack.push(button);
				return true;
			} else if (!visited.contains(button)) {
				visited.add(button);
				button.setText(String.valueOf(getNumBombNeighbors(r + 1, c)));
			}

		}
		return false;
	}

	private boolean isBomb(int i, int j) {
		try {
			return bombs.contains(cells[i][j]);
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		Minesweeper sweep = new Minesweeper();
		sweep.setVisible(true);

	}

}
