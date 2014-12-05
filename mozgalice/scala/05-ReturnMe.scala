def value: Int = {
  def one(x: Int): Int = { return x; 1 }
  val two = (x: Int) => { return x; 2 }
  1 + one(2) + two(3)
}

println(value)

/*
Šta je rezultat rada programa?

(A)
6

(B)
4

(C)
3

(D)
Greška u kompajliranju: unreachable code

*/