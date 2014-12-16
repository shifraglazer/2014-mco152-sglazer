package glazer.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */
public class Datebook {

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */

	Map<Integer, ArrayList<Event>> weekly;
	ArrayList<Event> daily;
	Map<Integer, ArrayList<Event>> monthly;
	Map<Integer, ArrayList<Event>> yearly;
	Map<Date, ArrayList<Event>> oneTime;

	public Datebook() {
		weekly = new HashMap<Integer, ArrayList<Event>>();
		daily = new ArrayList<Event>();
		monthly = new HashMap<Integer, ArrayList<Event>>();
		yearly = new HashMap<Integer, ArrayList<Event>>();
		oneTime = new HashMap<Date, ArrayList<Event>>();
	}

	public void addSingleEvent(Event event, Date date) {

		ArrayList<Event> events;
		if (oneTime.containsKey(date)) {
			events = oneTime.get(date);
		} else {
			events = new ArrayList<Event>();
		}
		events.add(event);
		oneTime.put(date, events);

	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {

		daily.add(event);

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		ArrayList<Event> events;
		if (weekly.containsKey(dayOfWeek)) {
			events = weekly.get(dayOfWeek);
		} else {
			events = new ArrayList<Event>();
		}
		events.add(event);
		weekly.put(dayOfWeek, events);

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		ArrayList<Event> events;
		if (monthly.containsKey(dayOfMonth)) {
			events = monthly.get(dayOfMonth);
		} else {
			events = new ArrayList<Event>();
		}
		events.add(event);
		monthly.put(dayOfMonth, events);
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		ArrayList<Event> events;
		if (yearly.containsKey(dayOfYear)) {
			events = yearly.get(dayOfYear);
		} else {
			events = new ArrayList<Event>();
		}
		events.add(event);
		yearly.put(dayOfYear, events);
	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		ArrayList<Event> calendar = new ArrayList<Event>();
		Date dates = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		ArrayList<Event> add = weekly.get(dayOfWeek);
		if (add != null) {
			for (Event e : add) {
				calendar.add(e);
			}
		}
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		add = monthly.get(dayOfMonth);
		if (add != null) {
			for (Event e : add) {
				calendar.add(e);
			}
		}
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		add = yearly.get(dayOfYear);
		if (add != null) {
			for (Event e : add) {
				calendar.add(e);
			}
		}

		for (Event e : daily) {
			calendar.add(e);
		}
		if (oneTime.containsKey(dates)) {
			for (Event e : oneTime.get(dates)) {
				calendar.add(e);
			}
		}
		Collections.sort(calendar);
		return calendar;
	}

}
