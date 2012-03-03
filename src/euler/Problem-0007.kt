package euler.problem0007

import euler.sequence.primes
import std.util.last

fun main(args : Array<String>) {
  val n = 10001
  println("the #$n prime number is ${primes().take(n).toList().last()}")
}
