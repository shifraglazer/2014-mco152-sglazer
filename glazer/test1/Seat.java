package glazer.test1;

public class Seat {

	private int row;
	private String letter;
	private boolean occupied;

	public Seat(int row, String letter) {
		this.letter = letter;
		this.row = row;
	}

	public Seat(int row, char letter) {
		this.letter = String.valueOf(letter);
		this.row = row;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getCode() {

		return row + letter;
	}

	@Override
	public String toString() {
		return "Seat [letter=" + letter + ", row=" + row + ", occupied="
				+ occupied + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((letter == null) ? 0 : letter.hashCode());
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Seat other = (Seat) obj;
		if (letter == null) {
			if (other.letter != null) {
				return false;
			}
		} else if (!letter.equals(other.letter)) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

}
