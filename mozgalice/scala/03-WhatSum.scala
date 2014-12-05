class C {
	def sum(x: Int = 1, y: Int = 2): Int = x + y
}
class D extends C {
	override def sum(y: Int = 3, x: Int = 4): Int = super.sum(x, y)
}
val d: D = new D
val c: C = d
c.sum(x = 0)
d.sum(x = 0)

/*
Šta je rezultat rada programa?

(A)
2
3

(B)
1
3

(C)
4
3

(D)
3
3

*/