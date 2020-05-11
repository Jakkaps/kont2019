package stuff;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class EverySecondMetaIteratorTest {

	@Test
	public void testEverySecondMetaIterator1() {
		// Should generate "1", "3"
		final EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4").iterator());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("1", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("3", metaIterator.next());
		Assert.assertFalse(metaIterator.hasNext());
	}

	@Test
	public void testThatIdentifiesACaseWhereTheCodeFails() {
		final EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4", "5").iterator());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("1", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("3", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("5", metaIterator.next());
		Assert.assertFalse(metaIterator.hasNext());
	}

	@Test
	public void testThatIdentifiesTheFundamentalBug() {
		final EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3").iterator());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertTrue(metaIterator.hasNext());
	}
}
