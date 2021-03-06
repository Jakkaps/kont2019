package stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaUtilities {

	/**
	 * Makes a copy of the strings in the provided list that satisfy the predicate
	 * @param strings list of strings
	 * @param pred predicate to satisfy
	 * @return the copy, without strings that does not satisfy the predicate
	 */
	public static List<String> copyIf(final List<String> strings, final Predicate<String> pred) {
	    return strings.stream().filter(pred).collect(Collectors.toList());
	}

	/**
	 * Adds n elements from the provided supplier to col and returns it.
	 * @param col
	 * @param n
	 * @param supplier
	 * @return the Collection that has been filled
	 */
	public static Collection<Integer> add(final Collection<Integer> col, final int n, final Supplier<Integer> supplier) {
	    for (int i = 0; i < n; i++){
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
		return add(col, n, () -> ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive));
	}

	/**
	 * Calls cons on all items in elements
	 * @param cons
	 * @param elements
	 */
	@SuppressWarnings("unchecked")
	public static void forEach(final Consumer<Object> cons, final Iterable<Object>... elements) {
    	for (Iterable<Object> element : elements) {
			element.forEach(cons);
    	}
	}

	/**
	 * Sums the result of applying the provided BinaryOperator op to pairs of consecutive elements of nums1 and nums2.
	 * If one of the sequences is longer than the other, you should drop the remaining elements.
	 * @param op the operator to apply on pairs of elements
	 * @param nums1 first set of elements
	 * @param nums2 second set of elements
	 * @return the sum of the result of applying op to pairs of consecutive elements of nums1 and nums2
	 */
	public static double zip(final BinaryOperator<Double> op, final List<Double> nums1, final Collection<Double> nums2) {
		double sum = 0;
		List<Double> nums2list = new ArrayList<>(nums2);

		int leastSize = Math.min(nums1.size(), nums2list.size());
		for (int i = 0; i < leastSize; i++) {
			sum += op.apply(nums1.get(i), nums2list.get(i));
		}

		return sum;
	}

	//@SuppressWarnings("unchecked")
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

		// Should print 13.0, i.e. the sum of the products
		System.out.println(zip((n1, n2) -> n1 * n2, Arrays.asList(1.0, 2.5), Arrays.asList(3.0, 4.0)));
	}
}
