package glazer.test1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals("    AB_CD_EF\n" + "001 .._.._..\n"
				+ "002 .._.._..\n" + "003 .._.._..\n", plane.toString());
	}

	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C",
				"2D", "2E", "2F", "3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals("    AB_CD_EF\n" + "001 OO_OO_OO\n"
				+ "002 OO_OO_OO\n" + "003 OO_OO_OO\n", plane.toString());
	}

	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_E", 3);
		Assert.assertEquals(15, plane.getNumSeats());
	}

	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_E", 5);
		plane.occupy("2D", "3A", "2A", "2B", "5A");
		Assert.assertEquals(20, plane.getNumEmptySeats());

	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD", 3);
		Assert.assertFalse(plane.isFull());
		plane.occupy("1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D", "3A",
				"3B", "3C", "3D");
		Assert.assertTrue(plane.isFull());
	}

	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("ABCD_EF_G", 3);
		try {
			plane.occupy("1A");
			plane.occupy("1I");
			Assert.fail();
		} catch (UnknownSeatException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOccupySeats() throws UnknownSeatException,
			FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_C", 4);
		plane.occupy("1A", "1B", "2A");
		Assert.assertTrue(plane.isOccupied("1A"));
		Assert.assertTrue(plane.isOccupied("1B"));
		Assert.assertTrue(plane.isOccupied("2A"));
		plane.occupySeats(2);
		Assert.assertEquals(7, plane.getNumEmptySeats());
		plane.occupySeats(2);
		Assert.assertEquals(5, plane.getNumEmptySeats());
		Seat seat = new Seat(3, "C");
		Seat newSeat = new Seat(4, "C");
		ArrayList<Seat> list = new ArrayList<Seat>();
		list.add(seat);
		list.add(newSeat);
		plane.occupy(list);
		Assert.assertTrue(plane.isOccupied("3C"));
		Assert.assertTrue(plane.isOccupied("4C"));
		plane = new Airplane("AB_CDE_FG", 3);
		List<Seat> seats = plane.occupySeats(3);
		System.out.println(seats.get(0).getCode());
		Assert.assertEquals("1C", seats.get(0).getCode());
		Assert.assertEquals("1D", seats.get(1).getCode());
		Assert.assertTrue(plane.isOccupied("1C"));
		plane.occupySeats(1);
		plane.occupySeats(2);

	}

	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException()
			throws FullPlaneException {
		Airplane plane = new Airplane("ABC_D", 2);
		try {
			plane.occupySeats(3);
			plane.occupySeats(1);
			plane.occupySeats(5);
			Assert.fail();
		} catch (NotEnoughSeatsTogeatherException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOccupySeatsThrowsFullPlaneException()
			throws NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_C", 3);

		try {
			plane.occupySeats(2);
			plane.occupySeats(2);
			plane.occupySeats(2);
			plane.occupySeats(1);
			plane.occupySeats(1);
			plane.occupySeats(1);
			plane.occupySeats(5);
			Assert.fail();
		} catch (FullPlaneException e) {
			e.printStackTrace();
		}
	}

}
