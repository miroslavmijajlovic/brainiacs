package rs.heapspace.java8.fn;

@FunctionalInterface
public interface HighOrderFunction {
	void doWithSomething(BasicFunction basicFunction);
}