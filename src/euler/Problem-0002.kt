package euler.problem0002

import euler.bigInt
import euler.multipleOf
import euler.sum
import euler.iterators.fibonacci

fun main(args : Array<String>) {
  val limit = bigInt(4000000)

  // average execution time of 0.5036 milliseconds over 10 iterations
  val numbers = fibonacci().takeWhile { it < limit }.filter { it multipleOf 2 }.toList()

  println("the sum of even Fibonacci numbers less than $limit is sum$numbers = ${numbers.sum()}")
}
