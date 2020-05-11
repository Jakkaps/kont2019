package stuff;

import java.util.Iterator;

public class StringBuilderCharacterIterator implements Iterator<Character> {
	private StringBuilder stringBuilder;
	private int index = 0;

	public StringBuilderCharacterIterator(final StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
	}

	@Override
	public boolean hasNext() {
		return index < stringBuilder.length();
	}

	@Override
	public Character next() {
		char next = stringBuilder.charAt(index);
		index++;
		return next;
	}
}
