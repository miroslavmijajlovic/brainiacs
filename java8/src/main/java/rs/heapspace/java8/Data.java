package rs.heapspace.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple data collections used in tests.
 */
public class Data {

	public static List<Integer> years = Arrays.asList(1975, 1982, 1834, 2015, 2001, 890);

	public static List<String> friends = Arrays.asList("Milos", "Jovana", "Ivan", "Tijana", "Jovan", "Vlada");

	public static Map<String, String> countries = new HashMap<String, String>();

	static {
		countries.put("RS", "Srbija");
		countries.put("ES", "Spain");
		countries.put("US", "United States");
		countries.put("ZA", "Zambia");
		countries.put("BR", "Brasil");
		countries.put("IT", "Italy");
	}

}