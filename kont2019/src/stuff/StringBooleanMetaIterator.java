package stuff;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class StringBooleanMetaIterator implements Iterator<String> {

	private Iterator<String> strings;
	private List<Boolean> booleans = new ArrayList<>();
	private int index = 0;

	public StringBooleanMetaIterator(final Iterator<String> strings, final Iterator<Boolean> booleans) {
	    this.strings = strings;
	    booleans.forEachRemaining(bool -> this.booleans.add(bool));
	}

	@Override
	public boolean hasNext() {
	    boolean remainingTrue = false;
	    for (int i = index; i < booleans.size(); i++) {
	    	if (booleans.get(i)){
	    		remainingTrue = true;
			}
		}
		return strings.hasNext() && remainingTrue; //Return wether the string has next and there are true's remaining
	}

	@Override
	public String next() {
		String next = "";
		for (int i = index; i < booleans.size(); i++) {
			next = strings.next();
			index++;
			if (booleans.get(i)){ // If next boolean is true, return the string value. Else continue loop.
				break;
			}
		}

		return next;
	}

	public static void main(final String[] args) {
		final Collection<String> strings = Arrays.asList("meta-iteratorer", "er", "ikke", "kult");
		final Collection<Boolean> booleans = Arrays.asList(true, true, false, true);

		final StringBooleanMetaIterator metaIterator = new StringBooleanMetaIterator(strings.iterator(), booleans.iterator());
		while (metaIterator.hasNext()) {
			System.out.print(metaIterator.next());
			System.out.print(" ");
		}
	}


}
