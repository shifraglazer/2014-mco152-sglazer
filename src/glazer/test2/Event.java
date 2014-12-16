package glazer.test2;

/**
 * An Event represents something that happens at a certain time of day
 */
public class Event implements Comparable<Event> {

	private String text;
	private int timeOfDay;

	/**
	 * @param text
	 *            is the description of the event
	 * @param timeOfDay
	 *            the time of day expressed in army time. So 0000 is 12:00AM and
	 *            2359 is 11:59PM.
	 */
	public Event(String text, int timeOfDay) {
		this.text = text;
		this.timeOfDay = timeOfDay;
	}

	public String getText() {
		return text;
	}

	public int getTimeOfDay() {
		return timeOfDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + timeOfDay;
		return result;
	}

	@Override
	public String toString() {
		return "Event [timeOfDay=" + timeOfDay + ", text=" + text + "]";
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
		Event other = (Event) obj;
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		if (timeOfDay != other.timeOfDay) {
			return false;
		}
		return true;
	}

	/**
	 * TODO: Fix this method The order should be ASCENDING. So 0000 before 2359.
	 */
	@Override
	public int compareTo(Event a) {
		return Integer.valueOf(timeOfDay).compareTo(a.timeOfDay);
	}

}
