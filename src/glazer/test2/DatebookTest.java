package glazer.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
		Datebook datebook = new Datebook();

		Event event = new Event("EVENT 1", 1200);
		datebook.addYearlyEvent(event, 23);
		Date today = getDate(2014, Calendar.JANUARY, 23);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		datebook.addMonthlyEvent(event, 14);
		Date today = getDate(2014, Calendar.MARCH, 14);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
		Event event2 = new Event("EVENT 2", 1000);
		datebook.addMonthlyEvent(event2, 14);
		list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event2, list.get(0));
		Assert.assertSame(event, list.get(1));
	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		datebook.addWeeklyEvent(event, 3);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
		Event event2 = new Event("EVENT 2", 1000);
		datebook.addWeeklyEvent(event2, 3);
		list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event2, list.get(0));
		Assert.assertSame(event, list.get(1));
	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		datebook.addDailyEvent(event);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
		Event event2 = new Event("EVENT 2", 1000);
		datebook.addDailyEvent(event2);
		list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertSame(event2, list.get(0));
		Assert.assertSame(event, list.get(1));
	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook datebook = new Datebook();
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		Event event = new Event("EVENT 1", 1200);
		Event event2 = new Event("EVENT 2", 1000);
		Event event3 = new Event("EVENT 3", 900);
		Event event4 = new Event("EVENT 4", 1700);
		Event event5 = new Event("EVENT 5", 2100);
		Event event6 = new Event("EVENT 6", 2300);
		datebook.addDailyEvent(event);
		datebook.addMonthlyEvent(event2, 25);
		datebook.addSingleEvent(event3, today);
		datebook.addYearlyEvent(event4, 329);
		datebook.addDailyEvent(event5);
		datebook.addMonthlyEvent(event6, 26);

		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		Assert.assertSame(event3, list.get(0));
		Assert.assertSame(event2, list.get(1));
		Assert.assertSame(event, list.get(2));
		Assert.assertSame(event4, list.get(3));
		Assert.assertSame(event5, list.get(4));
		Assert.assertTrue(!list.contains(event6));

	}

}
