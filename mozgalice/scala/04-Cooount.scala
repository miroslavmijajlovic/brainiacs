var x = 0
def counter = { x += 1; x }
def add(a: Int)(b: Int) = a + b
val adder1 = add(counter)(_)
val adder2 = add(counter) _
println("x = " + x)
println(adder1(10))
println("x = " + x)
println(adder2(10))
println("x = " + x)

/*
Šta je rezultat rada programa?

(A)
x = 1
12
x = 2
11
x = 2

(B)
x = 1
11
x = 1
12
x = 2

(C)
x = 0
11
x = 1
12
x = 2

(D)
x = 2
11
x = 2
12
x = 2

*/