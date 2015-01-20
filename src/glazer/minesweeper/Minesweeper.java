package glazer.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class Minesweeper extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// advanced level
	private final int ROWS = 16;
	private final int COLS = 30;
	private final int BOMBS = 99;
	private Cell cells[][];
	private Stack<Cell> stack;
	private JButton restart;
	private MouseListener a;
	private int seconds;
	private int hiddenBombs;
	// to display time
	private ScheduledExecutorService executor;
	private JLabel space;
	private JLabel space2;
	private JLabel secondTimer;
	private JLabel numBombs;
	private JPanel board;
	private JPanel topPanel;
	private JPanel bombPanel;
	private JPanel timerPanel;
	private DecimalFormat format;
	private DecimalFormat formatBombs;
	private ImageIcon pic;
	private ImageIcon flag;
	private ImageIcon exploadedBomb;
	private ImageIcon wrongBomb;
	private ImageIcon win;
	private ImageIcon open;
	private ImageIcon smile;
	private ImageIcon frown;
	private boolean winGame;
	private boolean endGame;
	private boolean start;

	public Minesweeper() {
		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		pic = new ImageIcon(getClass().getResource("newbomb.png"));
		smile = new ImageIcon(getClass().getResource("smile.gif"));
		flag = new ImageIcon(getClass().getResource("flag.png"));
		exploadedBomb = new ImageIcon(getClass().getResource(
				"exploadedBomb.png"));
		open = new ImageIcon(getClass().getResource("open.gif"));
		wrongBomb = new ImageIcon(getClass().getResource("expload.png"));
		frown = new ImageIcon(getClass().getResource("smileycons.png"));
		win = new ImageIcon(getClass().getResource("win.gif"));
		format = new DecimalFormat("000");
		formatBombs = new DecimalFormat("000");
		UIManager.put("ToggleButton.select", Color.LIGHT_GRAY);
		UIManager.put("Button.select", Color.LIGHT_GRAY);
		board = new JPanel();
		space2 = new JLabel();
		space2.setText("                                                 ");
		space = new JLabel();
		space.setText("                                                  ");
		bombPanel = new JPanel();
		numBombs = new JLabel();
		numBombs.setSize(20, 50);
		numBombs.setForeground(Color.RED);
		bombPanel.add(numBombs);
		bombPanel.setBackground(Color.BLACK);
		topPanel = new JPanel();
		topPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setSize(600, 25);
		timerPanel = new JPanel();
		timerPanel.setBackground(Color.BLACK);
		restart = new JButton();
		topPanel.add(bombPanel);
		board.setLayout(new GridLayout(ROWS, COLS));
		board.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createLoweredBevelBorder()));
		board.setBackground(Color.LIGHT_GRAY);
		cells = new Cell[ROWS][COLS];
		stack = new Stack<Cell>();
		topPanel.add(space2);
		topPanel.add(restart);
		restart.setSize(25, 25);
		restart.setBackground(Color.LIGHT_GRAY);
		restart.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		restart.setIcon(new ImageIcon(smile.getImage().getScaledInstance(25,
				25, Image.SCALE_SMOOTH)));
		secondTimer = new JLabel();
		secondTimer.setForeground(Color.RED);
		secondTimer.setSize(20, 50);
		topPanel.add(space);
		timerPanel.add(secondTimer);
		topPanel.add(timerPanel);
		numBombs.setText(formatBombs.format(hiddenBombs));
		container.add(topPanel, BorderLayout.NORTH);
		container.add(board, BorderLayout.CENTER);
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setUpGame();
			}

		};
		restart.addActionListener(listener);
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j] = new Cell(i, j);
				cells[i][j].setEnabled(true);
				cells[i][j].setMargin(new Insets(1, 1, 1, 1));
				cells[i][j].setBackground(Color.LIGHT_GRAY);

				a = new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {

						Object o = e.getSource();
						Cell button;
						if (o instanceof Cell) {
							button = (Cell) o;
							if (!start) {
								
								if (!button.isWhite()) {
									makeWhite(button);
								}
								start = true;
							}

							int both = InputEvent.BUTTON1_DOWN_MASK
									| InputEvent.BUTTON3_DOWN_MASK;
							if (!endGame && !winGame) {
								restart.setIcon(new ImageIcon(open.getImage()
										.getScaledInstance(25, 25,
												Image.SCALE_SMOOTH)));
							}
							if ((e.getModifiersEx() & (both)) == both) {
								rightPlusLeftClick(button);

							}

							else if (SwingUtilities.isLeftMouseButton(e)) {
								leftClick(button);
							} else if (SwingUtilities.isRightMouseButton(e)) {
								rightClick(button);
							}
							if (hiddenBombs == 0) {
								checkWin();
							}
						}

					}

					public void mouseReleased(MouseEvent e) {
						if (endGame && !winGame) {
							restart.setIcon(new ImageIcon(frown.getImage()
									.getScaledInstance(25, 25,
											Image.SCALE_SMOOTH)));
						} else if (winGame) {
							restart.setIcon(new ImageIcon(win.getImage()
									.getScaledInstance(25, 25,
											Image.SCALE_SMOOTH)));
						} else {
							restart.setIcon(new ImageIcon(smile.getImage()
									.getScaledInstance(25, 25,
											Image.SCALE_SMOOTH)));
						}
					}
				};
				cells[i][j].addMouseListener(a);
				board.add(cells[i][j]);
			}

		}
		Runnable timer = new Runnable() {
			public void run() {
				if (start && !endGame) {
					seconds++;
					secondTimer.setText(format.format(seconds));
				}
			}
		};
	
		setUpGame();
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(timer, 0, 1, TimeUnit.SECONDS);
	}

	public void makeWhite(Cell button) {
		int row = button.getRow();
		int col = button.getCol();
		if(!button.isWhite()){
		}
		// button.setWhite(true);
		int moveBomb = 0;
	
			for (int i = row - 1; i <= row + 1; i++) {
				for (int j = col - 1; j <= col + 1; j++) {
					try {
					if (cells[i][j].isBomb()) {
						cells[i][j].setBomb(false);
						moveBomb++;
					}
					} catch (Exception e) {
					}
				}
			}
		
		int num;
		int num2;
		Random random = new Random();
		while (moveBomb != 0) {
			num = random.nextInt(COLS);
			num2 = random.nextInt(ROWS);
			if (!cells[num2][num].isBomb()
					&& (num2 < row - 1 || num2 > row + 1)
					&& (num < col - 1 || num > col + 1)) {
				cells[num2][num].setBomb(true);
				moveBomb--;
			}
		}
	
		setUpNeighbors();

	}

	public void endGame() {
		/*
		 * int halfRow = ROWS / 2; int halfCol=COLS/2; EndThread thread = new
		 * EndThread(cells, 0, halfRow, halfCol, COLS, pic); thread.start();
		 * EndThread thread2 = new EndThread(cells, halfRow, ROWS, 0, halfCol,
		 * pic); thread2.start(); EndThread thread3 = new EndThread(cells,
		 * halfRow, ROWS, halfCol, COLS, pic); thread3.start();
		 */
		restart.setIcon(new ImageIcon(frown.getImage().getScaledInstance(25,
				25, Image.SCALE_SMOOTH)));
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				Cell button = cells[i][j];
				if (!button.isVisited()) {
					if (button.getflagged()) {
						if (!button.isBomb()) {
							button.setIcon(new ImageIcon(wrongBomb.getImage()
									.getScaledInstance(button.getWidth(),
											button.getHeight(),
											Image.SCALE_SMOOTH)));
							button.setVisited(true);
							button.setBorder(BorderFactory
									.createLineBorder(Color.GRAY));
						}
					} else if (button.isBomb()) {
						button.setSelected(true);
						button.setIcon(new ImageIcon(pic.getImage()
								.getScaledInstance(button.getWidth(),
										button.getHeight(), Image.SCALE_SMOOTH)));
						button.setVisited(true);
						button.setBorder(BorderFactory
								.createLineBorder(Color.GRAY));
					}

				}
			}
		}
	}

	public void checkWin() {
		boolean wins = true;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (cells[i][j].getflagged() && !cells[i][j].isBomb()) {
					wins = false;
				}
			}
		}
		if (wins) {
			restart.setIcon(new ImageIcon(win.getImage().getScaledInstance(25,
					25, Image.SCALE_SMOOTH)));
			winGame = true;
			endGame = true;
		}

	}

	public void setUpGame() {
		Random random = new Random();
		int num;
		int num2;
		hiddenBombs = 0;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j].setBomb(false);
			}
		}
		while (hiddenBombs < BOMBS) {
			num = random.nextInt(COLS);
			num2 = random.nextInt(ROWS);
			if (!cells[num2][num].isBomb()) {
				cells[num2][num].setBomb(true);
				hiddenBombs++;
			}
		}
		seconds = 0;
		setBombText();
		setUpNeighbors();
		start = false;
	}

	public void setUpNeighbors() {
		/*
		int halfrow=ROWS/2;
		int halfcol=COLS/2;
		SetupThread thread=new SetupThread(cells,0,halfrow,0,halfcol);
		SetupThread thread2=new SetupThread(cells,0,halfrow,halfcol,COLS);
		SetupThread thread3=new SetupThread(cells,halfrow,ROWS,0,halfcol);
		thread.start();
		thread2.start();
		thread3.start();
		
		*/
		for (int r =0; r <ROWS ; r++) {
			for (int j = 0; j < COLS; j++) {
				cells[r][j].setSelected(false);
				cells[r][j].setIcon(null);
				cells[r][j].setText(null);
				cells[r][j].setFont(null);
				cells[r][j].setflagged(false);
				cells[r][j].setRow(r);
				cells[r][j].setCol(j);
				cells[r][j].setVisited(false);
				Border raisedBorder = BorderFactory.createRaisedBevelBorder();
				cells[r][j].setBorder(raisedBorder);
				if (!cells[r][j].isBomb()) {
					int num = getNumBombNeighbors(r, j);
					if (num == 0) {
						cells[r][j].setWhite(true);
						cells[r][j].setNumNeighbors(0);
						cells[r][j].setBomb(false);

					} else {
						cells[r][j].setNumNeighbors(num);
						cells[r][j].setWhite(false);
						cells[r][j].setBomb(false);
					}
				} else {
					cells[r][j].setWhite(false);

				}
			}
		}
	
		secondTimer.setText(format.format(0));
		endGame = false;

		winGame = false;
		restart.setIcon(new ImageIcon(smile.getImage().getScaledInstance(25,
				25, Image.SCALE_SMOOTH)));
	}

	public void rightPlusLeftClick(Cell button) {
		int flagNeighbors = getNumFlagNeighbors(button);
		if (button.isVisited() && button.getNumNeighbors() > 0) {
			if (flagNeighbors == button.getNumNeighbors()) {
				exposeNeighbors(button);
			}
			button.setSelected(false);
			button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
	}

	public void exposeNeighbors(Cell button) {
		int r = button.getRow();
		int c = button.getCol();

		for (int i = r - 1; i <= r + 1; i++) {
			for (int j = c - 1; j <= c + 1; j++) {
				try {
					Cell test = cells[i][j];
					if (!test.isVisited()) {
						if (!test.isBomb()) {
							if (!test.getflagged()) {
								test.setVisited(true);
								if (test.isWhite()) {
									test.setSelected(true);
									test.setBorder(BorderFactory
											.createLineBorder(Color.GRAY));
									findWhiteNeighbors(test);
								} else {
									test.setSelected(true);
									setText(test);
									test.setBorder(BorderFactory
											.createLineBorder(Color.GRAY));
								}
							} else {
								test.setIcon(new ImageIcon(wrongBomb.getImage()
										.getScaledInstance(test.getWidth(),
												test.getHeight(),
												Image.SCALE_SMOOTH)));
								test.setVisited(true);
								test.setBorder(BorderFactory
										.createLineBorder(Color.GRAY));
								endGame = true;
							}
						}
					} else if (test.isBomb() && !test.getflagged()) {
						test.setIcon(new ImageIcon(pic.getImage()
								.getScaledInstance(button.getWidth(),
										button.getHeight(), Image.SCALE_SMOOTH)));
						test.setBorder(BorderFactory
								.createLineBorder(Color.GRAY));
						endGame = true;
					}

				} catch (Exception e) {

				}
			}
		}
		if (endGame) {
			endGame();
		}

	}

	public void rightClick(Cell button) {

		if (!button.isVisited()) {
			if (endGame) {
				button.setSelected(false);
			} else if (button.getflagged()) {
				button.setflagged(false);
				button.setIcon(null);
				button.setSelected(false);
				hiddenBombs++;
				setBombText();
			} else {
				button.setflagged(true);
				button.setSelected(true);
				button.setIcon(new ImageIcon(flag.getImage().getScaledInstance(
						button.getWidth(), button.getHeight(),
						Image.SCALE_SMOOTH)));
				hiddenBombs--;
				setBombText();
			}

		}

	}

	public void leftClick(Cell button) {
		if (!button.isVisited()) {
			if (endGame) {
				if (!button.getflagged()) {
					button.setSelected(true);
				} else {
					button.setSelected(false);
				}
			} else if (!button.getflagged()) {
				button.setVisited(true);
				if (button.isBomb()) {
					button.setIcon(new ImageIcon(exploadedBomb.getImage()
							.getScaledInstance(button.getWidth(),
									button.getHeight(), Image.SCALE_SMOOTH)));
					button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					endGame();
					endGame = true;
				} else if (button.isWhite()) {
					button.setIcon(null);
					button.setSelected(false);
					button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					findWhiteNeighbors(button);
				} else {
					button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					button.setIcon(null);
					setText(button);
				}
			} else if (button.isSelected() && button.getflagged()) {
				button.setSelected(false);
			} else if (button.isWhite()) {
				button.setIcon(null);
				button.setSelected(true);
				button.setBackground(null);
				button.setForeground(null);
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		} else if (button.isVisited()) {
			button.setSelected(false);

		}
	}

	public void findWhiteNeighbors(Cell button) {
		stack.push(button);
		button.setVisited(true);
		while (!stack.isEmpty()) {
			boolean change = false;
			do {

				change = false;
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

			stack.pop();
		}
	}

	public boolean checkCell(Cell button) {
		if (!button.getflagged()) {
			if (button.isWhite() & !button.isVisited()) {
				
				button.setBackground(Color.LIGHT_GRAY);
				button.setSelected(true);
				button.setVisited(true);
				stack.push(button);
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				return true;
			} else if (!button.isVisited()) {
				button.setVisited(true);
				setText(button);
				button.setSelected(true);
				button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
		}
		return false;
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
	
	public int getNumFlagNeighbors(Cell button) {
		int i = button.getRow();
		int j = button.getCol();
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


	public boolean hasRight() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c + 1 < COLS) {
			Cell button = cells[r][c + 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasUpRight() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c + 1 < COLS && r - 1 >= 0) {
			Cell button = cells[r - 1][c + 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasDownRight() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c + 1 < COLS && r + 1 < ROWS) {
			Cell button = cells[r + 1][c + 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasLeft() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c - 1 >= 0) {
			Cell button = cells[r][c - 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasUpLeft() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c - 1 >= 0 && r - 1 >= 0) {
			Cell button = cells[r - 1][c - 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasDownLeft() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (c - 1 >= 0 && r + 1 < ROWS) {
			Cell button = cells[r + 1][c - 1];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasUp() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (r - 1 >= 0) {
			Cell button = cells[r - 1][c];
			return checkCell(button);
		}
		return false;
	}

	public boolean hasDown() {
		int r = stack.peek().getRow();
		int c = stack.peek().getCol();
		if (r + 1 < ROWS) {
			Cell button = cells[r + 1][c];
			return checkCell(button);
		}
		return false;
	}

	public void setBombText() {
		if (hiddenBombs < 0) {
			formatBombs = new DecimalFormat(" #00");
			numBombs.setText(formatBombs.format(hiddenBombs));
		} else {
			formatBombs = new DecimalFormat("000");
			numBombs.setText(formatBombs.format(hiddenBombs));
		}
	}

	public void setText(Cell button) {
		button.setVisited(true);
		int num = button.getNumNeighbors();
		switch (num) {
		case 1: {
			button.setForeground(Color.BLUE);
			break;
		}
		case 2: {
			button.setForeground(new Color(0x00, 0xC0, 0x00));
			break;
		}
		case 3: {
			button.setForeground(Color.RED);
			break;
		}
		case 4: {
			button.setForeground(new Color(13, 30, 125));
			break;

		}
		case 5: {
			button.setForeground(new Color(128, 128, 0));
			break;
		}
		case 6: {
			button.setForeground(Color.decode("#2B8B9E"));
			break;
		}
		case 7: {
			button.setForeground(Color.BLACK);
			break;
		}
		case 8: {
			button.setForeground(Color.GRAY);
			break;
		}
		}
		button.setFont(new Font("Arial", Font.BOLD, 10));
		button.setText(String.valueOf(num));
	}

	public boolean isFlag(int i, int j) {
		try {
			return cells[i][j].getflagged();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isBomb(int i, int j) {
		try {
			return cells[i][j].isBomb();
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {

		try {

			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());

		}

		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException

				| UnsupportedLookAndFeelException e) {

			e.printStackTrace();

		}
		Minesweeper sweep = new Minesweeper();
		sweep.setVisible(true);
	}

}
