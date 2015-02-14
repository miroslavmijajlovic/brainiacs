package rs.heapspace.java8.fn;

import java.util.function.DoubleUnaryOperator;

public class Converter {

	// Currying
	public static DoubleUnaryOperator convert(double factor, double base) {
		return amount -> amount * factor + base;
	}

	public static void main(String[] args) {
		DoubleUnaryOperator convertCtoF = convert(1.8, 3.2);

		System.out.println(convertCtoF.applyAsDouble(10));

		DoubleUnaryOperator convertKmToMi = convert(0.62137, 0);

		System.out.println(convertKmToMi.applyAsDouble(10));
	}
}