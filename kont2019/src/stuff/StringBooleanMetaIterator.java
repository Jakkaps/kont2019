package stuff;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class StringBooleanMetaIterator implements Iterator<String> {

	private final Iterator<String> strings;
	private final Iterator<Boolean> booleans;

	public StringBooleanMetaIterator(final Iterator<String> strings, final Iterator<Boolean> booleans) {
		this.strings = strings;
		this.booleans = booleans;
		nextBoolean();
	}

	@Override
	public boolean hasNext() {
		return strings.hasNext();
	}

	private void nextBoolean() {
		while (booleans.hasNext() && (! booleans.next())) {
			strings.next();
		}
	}

	@Override
	public String next() {
		final String next = strings.next();
		nextBoolean();
		return next;
	}

	public static void main(final String[] args) {
		final Collection<String> strings = Arrays.asList("meta-iteratorer", "er", "ikke", "kult");
		final Collection<Boolean> booleans = Arrays.asList(true, true, false, true);

		// skal skrive ut
		// meta-iteratorer er kult
		final StringBooleanMetaIterator metaIterator = new StringBooleanMetaIterator(strings.iterator(), booleans.iterator());
		while (metaIterator.hasNext()) {
			System.out.print(metaIterator.next());
			System.out.print(" ");
		}
	}
}