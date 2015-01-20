package glazer.minesweeper;

import javax.swing.JButton;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isFlagged;
	private boolean isBomb;
	private int numNeighbors;
	private boolean isVisited;
	private int row;
	private int col;

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	private boolean isWhite;

	public Cell(boolean isflagged, boolean isbomb, int numNeighbors,
			boolean isVisited, int row, int col, boolean isWhite) {
		this.isFlagged = isflagged;
		this.isBomb = isbomb;
		this.numNeighbors = numNeighbors;
		this.isVisited = isVisited;
		this.row = row;
		this.col = col;
		this.isWhite = false;
	}

	public Cell(int row, int col) {
		this.isFlagged = false;
		this.isBomb = false;
		this.numNeighbors = 0;
		this.isVisited = false;
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean getflagged() {
		return isFlagged;
	}

	public void setflagged(boolean isflagged) {
		this.isFlagged = isflagged;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isbomb) {
		this.isBomb = isbomb;
	}

	public int getNumNeighbors() {
		return numNeighbors;
	}

	public void setNumNeighbors(int numNeighbors) {
		this.numNeighbors = numNeighbors;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

}
