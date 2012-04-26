package euler.problem0001

import euler.multipleOf
import euler.sum

fun main(args : Array<String>) {
  val limit = 1000

  // average execution time of 0.3447 milliseconds over 10 iterations
  val result = (1..limit).filter { n -> n multipleOf 3 || n multipleOf 5 }.sum()

  println("the sum of all the multiples of 3 or 5 below $limit is $result")
}
