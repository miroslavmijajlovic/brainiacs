package rs.heapspace.java8.fn;

public class Run2 {

	public static void main(String[] args) {
		BasicFunction fn = () -> System.out.println("Fun!");

		HighOrderFunction highOrderFunction = (f -> {
			f.doSomething();
			f.doSomething();
		});

		highOrderFunction.doWithSomething(fn);

	}
}