package rs.heapspace.java8.streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImpVsStreams {

	public static String doItImperative(List<String> words) {
		StringBuilder sb = new StringBuilder();
		Set<Character> charsSet = new HashSet<>();
		for (String word : words) {
			for (char singleChar : word.toCharArray()) {
				if (!charsSet.contains(singleChar)) {
					sb.append(singleChar);
					charsSet.add(singleChar);
				}
			}
		}
		return sb.toString();
	}

	public static String doItDeclartive(List<String> words) {
		return words.stream()
					.map(str -> str.split(""))
					.flatMap(Arrays::stream)
					.distinct()
					.collect(Collectors.joining());
	}

	public static void main(String[] args) {
		List<String> words = Arrays.asList("HeapSpace", "je", "otvorena", "tehnološka", "zajednica");

		System.out.println(doItImperative(words));
		System.out.println(doItDeclartive(words));


	}
}