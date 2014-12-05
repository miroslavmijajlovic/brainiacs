
class X
val x = new X
val y = x : x.type

object Overload {
  def foo(arg: Any) = 1
  def foo(arg: x.type) = 2
}

println(Overload.foo(x))
println(Overload.foo(y : y.type))

/*
Šta je rezultat rada programa?

(A)
1
1

(B)
1
2

(C)
2
1

(D)
2
2

*/