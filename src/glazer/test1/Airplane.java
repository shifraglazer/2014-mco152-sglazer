package glazer.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Airplane {
	private int seatsPerRow;
	private String configuration;
	private int rows;
	private String[][] seats;
	private String[] sets;
	private Map<String, Seat> map;
	private int seatsFull;

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.configuration = configuration;
		this.rows = numRows;
		this.seatsFull = 0;
		sets = configuration.split("_");
		for (String s : sets) {
			seatsPerRow += s.length();
		}
		char letter = 'A';
		seats = new String[rows][seatsPerRow];
		map = new HashMap<String, Seat>();
		int num;
		String code;
		Seat seat;
		for (int i = 0; i < rows; i++) {
			letter = 'A';
			for (int j = 0; j < seatsPerRow; j++) {
				num = i + 1;
				code = String.valueOf(num) + letter;
				seat = new Seat(i + 1, letter);
				seat.setOccupied(false);
				seats[i][j] = code;
				map.put(code, seat);
				letter += 1;
				num = 0;

			}
		}
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		return getNumSeats() - seatsFull;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		return getNumSeats() == seatsFull;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if (map.containsKey(code)) {
			return map.get(code).isOccupied();
		} else {
			throw new UnknownSeatException();
		}
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied)
			throws UnknownSeatException {
		if (map.containsKey(code)) {
			map.get(code).setOccupied(true);
			seatsFull++;
		} else {
			throw new UnknownSeatException();
		}

	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for (String code : codes) {
			if (map.containsKey(code)) {
				map.get(code).setOccupied(true);
				seatsFull++;
			} else {
				throw new UnknownSeatException();
			}
		}
	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for (Seat s : seats) {
			s.setOccupied(true);
			map.get(s.getCode()).setOccupied(true);
			seatsFull++;
		}
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (map.containsKey(code)) {
			return map.get(code);
		} else {
			throw new UnknownSeatException();
		}
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return rows * seatsPerRow;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat("000");

		builder.append("    " + configuration + "\n");
		for (int i = 0; i < seats.length; i++) {
			builder.append(formatter.format(i + 1));
			builder.append(" ");
			for (int j = 0, seatNumber = 0; j < sets.length; j++) {
				for (int k = 0; k < sets[j].length(); k++, seatNumber++) {
					if (map.get(seats[i][seatNumber]).isOccupied()) {
						builder.append("O");
					} else {
						builder.append(".");
					}

				}
				if (seatNumber < seatsPerRow) {
					builder.append("_");
				}
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather)
			throws FullPlaneException, NotEnoughSeatsTogeatherException {
		if (isFull()) {
			throw new FullPlaneException();
		}
		String code;
		int numSeatsTogether = numSeatsTogeather;
		List<Seat> occupied = new ArrayList<Seat>();
		int col = 0;
		Integer rowNum;
		Seat unoccupied;
		for (int i = 0; i < sets.length; i++) {
			if (numSeatsTogether <= sets[i].length()) {
				for (int j = 0; j < rows; j++) {

					occupied = new ArrayList<Seat>();
					for (int k = 0; k < sets[i].length(); k++, col++) {

						rowNum = j + 1;
						code = rowNum.toString();
						char letter = 'A';
						letter += col;
						code += letter;
						if (k == sets[i].length() - 1 && j != rows - 1) {
							col -= sets[i].length();
						}

						unoccupied = map.get(code);
						if (!unoccupied.isOccupied()) {
							occupied.add(unoccupied);

							if (occupied.size() == numSeatsTogether) {
								for (Seat s : occupied) {
									s.setOccupied(true);
									seatsFull++;
								}
								return occupied;
							}
						}

					}

				}

			} else {
				col += sets[i].length();
			}

		}
		throw new NotEnoughSeatsTogeatherException();

	}
}
