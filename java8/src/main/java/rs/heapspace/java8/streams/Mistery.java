package rs.heapspace.java8.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Mistery {

	public static HashMap<String,Integer> itDoesSomething(List<String> elements) {
	    HashMap<String,Integer> results = new HashMap<>();
	    for (String element: elements) {
	        Integer count = results.get(element);
	        if (count != null) {
	            results.put(element, count + 1);
	        } else {
	            results.put(element, 1);
	        }
	    }
	    return results;
	}

	public static Map<String,Integer> itDoesSomething2(List<String> elements) {
	   Map<String,Integer> results = new HashMap<>();
	   elements.stream()
	           .collect(groupingBy(Function.<String>identity()))
	           .forEach(
				   (e, elementsInGroup) ->
					   results.put(e, elementsInGroup.size()));
	    return results;
	}


	public static void main(String[] args) {
		List<String> words = Arrays.asList("HeapSpace", "je", "HeapSpace", "zajednica");

		Map<String, Integer> data = itDoesSomething(words);
		System.out.println(data.get("HeapSpace"));

		data = itDoesSomething2(words);
		System.out.println(data.get("HeapSpace"));
	}
}