package rs.heapspace.java8.fn;

public class Run1 {

	public static void main(String[] args) {
		BasicFunction fn = StaticFunction::pureFun;
		fn.doSomething();
		doItTwice(fn);
	}

	public static void doItTwice(BasicFunction fn) {
		fn.doSomething();
		fn.doSomething();
	}
}