package glazer.minesweeper;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class SetupThread extends Thread {
	private Cell[][] cells;
	private int start;
	private int end;
	private int end2;
	private int start2;

	public SetupThread(Cell[][] cells, int start, int end, int start2, int end2) {
		this.cells = cells;
		this.start = start;
		this.end = end;
		this.end2 = end2;
		this.start2 = start2;
	}
	@Override
	public void run(){
		for (int r = start; r < end; r++) {
			for (int j = start2; j < end2; j++) {
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
	}
	/*
	public int getNumBombNeighbors(int i, int j) {
		int numBomb = 0;
		for(int r=i-1;r<=i+1;r++){
			for(int c=j-1;c<=j+1;j++){
				try{
				if(cells[r][c].isBomb()&& !(i==r&&j==c)){
					numBomb++;
				}
				}
				catch(Exception e){}
			}
		}
		return numBomb;
	}
	*/
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

	public boolean isBomb(int i, int j) {
		try {
			return cells[i][j].isBomb();
		} catch (Exception e) {
			return false;
		}
	}

}
