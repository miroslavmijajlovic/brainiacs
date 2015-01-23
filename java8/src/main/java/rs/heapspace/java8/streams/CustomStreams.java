package rs.heapspace.java8.streams;

import rs.heapspace.java8.RunUtil;

import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Custom streams are created 'out of thin air' and they
 * are not backed by some collection.
 */
public class CustomStreams {

	/**
	 * Sum all integers in [1,100] range.
	 */
	public static void sumAllIntegers() {
		int sum = IntStream.range(1, 101).sum();

		System.out.println("sum = " + sum);
	}

	/**
	 * Using summary stats for 100 random ints.
	 */
	public static void randomInts() {
		IntSummaryStatistics stats = new Random()
			.ints(100, 0, 100)
			.summaryStatistics();

		System.out.println("max: " + stats.getMax());
		System.out.println("min: " + stats.getMin());
		System.out.println("avg: " + stats.getAverage());
	}

	/**
	 * Creates random gaussian stream and prints the ranges.
	 */
	public static void gaussian() {
		Random random = new Random();

		// creates stream
		DoubleStream gaussianStream = Stream
			.generate(random::nextGaussian)
			.mapToDouble(e -> e);			// boxing double to Double


		LinkedHashMap<Ranges.Range, Integer> gaussianRangeCountMap =
			gaussianStream
				.filter(e -> (e >= -1.0 && e < 1.0))		// filter inputs for range [-1.0, 1.0)
				.limit(1000000)								// this number of numbers
				.boxed()
				.map(Ranges::of)				// map each random number to its range
				.collect(						// and now perform terminal collection
					Ranges::emptyRangeCountMap,			// define collector
					(m, e) -> m.put(e, m.get(e) + 1),
					Ranges::mergeRangeCountMaps);

		gaussianRangeCountMap.forEach(
			(k, v) -> System.out.printf("%+4.2f\t%d\n", k.from(), v));
	}

	public static void main(String[] args) {
		RunUtil.runAllPublicStaticMethods(CustomStreams.class);
	}

}