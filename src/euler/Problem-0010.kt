package euler.problem0010

import euler.sum
import euler.iterators.primes

fun main(args : Array<String>) {
  val limit = 2000000

  // average execution time of 2.615 seconds over 10 iterations
  val result = primes().takeWhile { it < limit }.toList()

  println("the sum of all the ${result.size()} primes below $limit is ${result.sum()}")
}
