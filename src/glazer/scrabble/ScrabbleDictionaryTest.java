package glazer.scrabble;

import org.junit.Assert;

import org.junit.Test;

public class ScrabbleDictionaryTest {

	@Test
	//want to make sure will contain true when is in dictionary
	public void testContainsTrue() {
		ScrabbleDictionary dictionary= new ScrabbleDictionary();
		Assert.assertTrue(dictionary.contains("Hello"));
		Assert.assertTrue(dictionary.contains("HELLO"));
	}
	@Test
	//want to make sure will contain false when it is not in dictionary
	public void testContainsFalse() {
		ScrabbleDictionary dictionary= new ScrabbleDictionary();
		Assert.assertFalse(dictionary.contains("djfkdnfjd"));
		Assert.assertFalse(dictionary.contains("dkn"));
	}
	@Test
	//want to make sure not null
	public void testContainsNull() {
		ScrabbleDictionary dictionary= new ScrabbleDictionary();
		Assert.assertFalse(dictionary.contains(null));
		
	}
}
