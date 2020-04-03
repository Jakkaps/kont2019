package stuff;

import java.util.Iterator;

import org.junit.Assert;

public class IteratorTester {

	public static <T> void testIterator(final Iterator<T> actual, final T... expected) {
		for (int i = 0; i < expected.length; i++) {
			Assert.assertTrue("Should still be elements left", actual.hasNext());
			Assert.assertEquals(expected[i], actual.next());
		}
		Assert.assertFalse("Should be at the end of the iterator", actual.hasNext());
	}
}
