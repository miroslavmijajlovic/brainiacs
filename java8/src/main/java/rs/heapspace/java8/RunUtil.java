package rs.heapspace.java8;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class RunUtil {

	/**
	 * Runs all public static methods in give class.
	 */
	public static void runAllPublicStaticMethods(Class target) {
		Method[] methods = target.getMethods();

		Arrays.stream(methods)
			.filter(method -> method.getParameterCount() == 0)
			.filter(method -> Modifier.isStatic(method.getModifiers()))
			.sorted((Method m1, Method m2) -> m1.getName().compareTo(m2.getName()))
			.forEach(method -> {
				System.out.println("\n--- " + method.getName() + " ----------\n");
				try {
					method.invoke(null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
	}
}