package rs.heapspace.java8.fn;

public class Run1 {

	public static void main(String[] args) {
//		anonymous class
//		BasicFunction fn = new BasicFunction() {
//			@Override
//			public void doSomething() {
//				StaticFunction.pureFun();
//			}
//		};

		// lambda 1
//		BasicFunction fn = () -> {
//			StaticFunction.pureFun();
//		};

		// lambda 2
//		BasicFunction fn = () -> StaticFunction.pureFun();

		// method reference
		BasicFunction fn = StaticFunction::pureFun;
		fn.doSomething();
		doItTwice(fn);
	}

	public static void doItTwice(BasicFunction fn) {
		fn.doSomething();
		fn.doSomething();
	}
}