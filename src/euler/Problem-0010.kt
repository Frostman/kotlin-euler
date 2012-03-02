package euler.problem0010

import euler.sum
import euler.sequence.primes

fun main(args : Array<String>) {
  val limit = 2000000
  val result = primes().takeWhile { it < limit }.toList()
  println("the sum of all the ${result.size()} primes below $limit is ${result.sum()}")
}
