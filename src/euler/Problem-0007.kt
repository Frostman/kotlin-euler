package euler.problem0007

import euler.iterators.primes

fun main(args : Array<String>) {
  val n = 10001
  // average execution time of 38.2269 milliseconds over 10 iterations
  println("the #$n prime number is ${primes().take(n).toList().last()}")
}
