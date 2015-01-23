package rs.heapspace.java8.streams;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static rs.heapspace.java8.Data.friends;
import static rs.heapspace.java8.Data.years;
import static rs.heapspace.java8.RunUtil.runAllPublicStaticMethods;

/**
 * Java8 examples of using streams on <code>List</code>.
 */
public class ListStreams {

	/**
	 * Iterates elements of the stream and executes
	 * lambda expression on each element.
	 */
	public static void iterateStream() {
		// long version
		//years.stream().forEach(year -> System.out.println(year));

		// shorter version, using just method reference
		years.stream().forEach(System.out::println);
	}

	/**
	 * Modifies each element using <code>map</code> method
	 * and collects results into new list.
	 */
	public static void modifyStream() {
		List<Integer> centuries =
			years
			.stream()
			.map(year -> year / 100)			// divide each year by 100
			.collect(Collectors.toList());		// collect into new list

		centuries.stream().forEach(System.out::println);
	}

	/**
	 * Modifies stream and collects to specific implementation.
	 */
	public static void modifyStreamAndSpecificCollect() {
		List<Integer> centuries =
			years
			.stream()
			.map(year -> year / 100)								// divide each year by 100
			.collect(Collectors.toCollection(LinkedList::new));		// collect into specific collection implementation.

		System.out.println(centuries.getClass());
		centuries.stream().forEach(System.out::println);
	}

	/**
	 * Modifies each element and the filter elements.
	 * Finally, counts remaining elements in the stream.
	 */
	public static void modifyAndFilterStream() {
		long numberOfYearsInThisCentury =
			years
			.stream()
			.map(year -> year / 100)			// divide each year by 100
			.filter(century -> century >= 20)	// only recent centuries
			.count();

		System.out.println("numberOfYearsInThisCentury = " + numberOfYearsInThisCentury);
	}


	private static Function<Integer, Integer> yearToCentury = year -> year / 100;
	private static Predicate<Integer> isThisCentury = century -> century >= 20;

	/**
	 * The same example as {@link #modifyAndFilterStream() above},
	 * but with using stored lambdas.
	 */
	public static void modifyAndFilterStreamWithLambdas() {
		long numberOfYearsInThisCentury =
			years
			.stream()
			.map(yearToCentury)
			.filter(isThisCentury)
			.count();

		System.out.println("numberOfYearsInThisCentury = " + numberOfYearsInThisCentury);
	}

	/**
	 * Finds longest name length.
	 */
	public static void maxNameLength() {
		OptionalInt maxNameLen = friends
			.stream()
			.mapToInt(String::length)
			.max();

		System.out.println("maxNameLen = " + maxNameLen.getAsInt());
	}

	/**
	 * Iterates list using indices and collects all objects into single
	 * string, separated by delimeter.
	 */
	public static void iterateWithIndices() {
		String out = IntStream.range(0, friends.size())
			.mapToObj(i -> friends.get(i))
			.sorted()
			.collect(Collectors.joining(", "));

		System.out.println(out);
	}

	/**
	 * Reduces the list to the longest name.
	 */
	public static void reduceListToLongestName() {
		String longestNamefriends =
			friends.stream()
				.reduce((String longest, String name) -> {		// first argument is the accumulated value
					if (name.length() > longest.length()) {		// and the second argument is the current element
						return name;
					}
					return longest;
				})
				.get();

		System.out.println("longestNamefriends = " + longestNamefriends);
	}


	public static void main(String[] args) {
		runAllPublicStaticMethods(ListStreams.class);
	}

}