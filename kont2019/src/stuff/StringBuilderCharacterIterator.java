package stuff;

import java.util.Iterator;

public class StringBuilderCharacterIterator implements Iterator<Character> {

	private final StringBuilder stringBuilder;

	public StringBuilderCharacterIterator(final StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}

	private int pos = 0;

	@Override
	public boolean hasNext() {
		return pos < stringBuilder.length();
	}

	@Override
	public Character next() {
		final char c = stringBuilder.charAt(pos);
		pos++;
		return c;
	}
}
