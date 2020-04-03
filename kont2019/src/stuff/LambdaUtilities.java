package stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaUtilities {

	/**
	 * Makes a copy of the strings in the provided list that satisfy the predicate
	 * @param strings list of strings
	 * @param pred predicate to satisfy
	 * @return the copy, without strings that does not satisfy the predicate
	 */
	public static List<String> copyIf(final List<String> strings, final Predicate<String> pred) {
		final List<String> copy = new ArrayList<>();
		for (final String string : strings) {
			if (pred.test(string)) {
				copy.add(string);
			}
		}
		return copy;
	}

	/**
	 * Adds n elements from the provided supplier to col
	 * @param col
	 * @param n
	 * @param supplier
	 * @return the Collection that has been filled
	 */
	public static Collection<Integer> add(final Collection<Integer> col, int n, final Supplier<Integer> supplier) {
		while (n-- > 0) {
			col.add(supplier.get());
		}
		return col;
	}

	/**
	 * Fills col with n random numbers in the range [minInclusive, maxExclusive>.
	 * It must use fill method above!
	 * @param col
	 * @param n
	 * @param minInclusive
	 * @param maxExclusive
	 */
	public static Collection<Integer> addRandoms(final Collection<Integer> col, final int n, final int minInclusive, final int maxExclusive) {
		return add(col, n, () -> (int) (minInclusive + Math.random() * (maxExclusive - minInclusive)));
	}

	/**
	 * Calls cons on all items in all elements
	 * @param cons
	 * @param elements
	 */
	@SuppressWarnings("unchecked")
	public static void forEach(final Consumer<Object> cons, final Iterable<Object>... elements) {
		for (int i = 0; i < elements.length; i++) {
			elements[i].forEach(cons);
		}
	}

	/**
	 * Sums the result of applying op to consecutive elements of nums1 and nums2
	 * @param op
	 * @param nums1
	 * @param nums2
	 * @return the sum of the result of applying op to consecutive elements of nums1 and nums2
	 */
	public static double zip(final BinaryOperator<Double> op, final List<Double> nums1, final Collection<Double> nums2) {
		final Iterator<Double> it1 = nums1.iterator(), it2 = nums2.iterator();
		double result = 0;
		while (it1.hasNext() && it2.hasNext()) {
			result += op.apply(it1.next(), it2.next());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		// Should print [lambda, er, kult]
		System.out.println(copyIf(Arrays.asList("lambda", "er", "ikke", "kult"), s -> ! s.equals("ikke")));
		// Should print [3, 2, 1]
		final Iterator<Integer> it = Arrays.asList(3, 2, 1).iterator();
		System.out.println(add(new ArrayList<>(), 3, () -> it.next()));
		// Should print
		// 3
		// 2
		// 1
		forEach(System.out::println, Arrays.asList("3", "2"), Arrays.asList("1"));
		// Should print 13.0 (i.e. the sum of the products)
		System.out.println(zip((n1, n2) -> n1 * n2, Arrays.asList(1.0, 2.5), Arrays.asList(3.0, 4.0)));
	}
}
