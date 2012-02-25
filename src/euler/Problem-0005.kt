package euler.problem0005

fun main(args : Array<String>) {
  val range = 1..20
  println("the least common multiple of numbers from ${range.start} to ${range.end} is ${leastCommonMultipleOf(range)}")
}

inline fun leastCommonMultipleOf(range: IntRange): Int {
  var number = range.end
  while (true) if (number.isDivisibleByNumbersIn(range)) break else number++
  return number
}

inline fun Int.isDivisibleByNumbersIn(range: IntRange) = range all { this % it == 0 }
