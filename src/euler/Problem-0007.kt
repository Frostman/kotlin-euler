package euler.problem0007

import euler.iterators.primes

fun main(args : Array<String>) {
  val n = 10001
  println("the #$n prime number is ${primes().take(n).toList().last()}")
}
