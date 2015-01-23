package rs.heapspace.java8.streams;

import java.util.stream.LongStream;

import static rs.heapspace.java8.RunUtil.runAllPublicStaticMethods;

public class ParallelStreams {

	public static void calcSequential() {
		long time = System.currentTimeMillis();

		long sum = LongStream.range(1, 1_000_000_000).sequential().sum();

		System.out.println("sum = " + sum);
		System.out.println("elapsed = " + (System.currentTimeMillis() - time) + "ms");
	}

	public static void calcParallel() {
		long time = System.currentTimeMillis();

		long sum = LongStream.range(1, 1_000_000_000).parallel().sum();

		System.out.println("sum = " + sum);
		System.out.println("elapsed = " + (System.currentTimeMillis() - time) + "ms");
	}

	public static void main(String[] args) {
		runAllPublicStaticMethods(ParallelStreams.class);
	}

}