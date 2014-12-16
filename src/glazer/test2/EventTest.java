package glazer.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class does not need to be edited
 */
public class EventTest {

	@Test
	public void testCompareTo() {
		Event a = new Event("BEFORE", 0);
		Event b = new Event("AFTER", 2359);

		Assert.assertTrue(b.compareTo(b) == 0);
		Assert.assertTrue(a.compareTo(a) == 0);
		Assert.assertTrue(a.compareTo(b) < 0);
		Assert.assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void testSort() {
		Event a = new Event("BEFORE", 0);
		Event b = new Event("AFTER", 2359);
		List<Event> events = new ArrayList<Event>();
		events.add(b);
		events.add(a);
		Collections.sort(events);

		Assert.assertEquals(a, events.get(0));
		Assert.assertEquals(b, events.get(1));
	}

}
