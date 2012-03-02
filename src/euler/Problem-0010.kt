package euler.problem0010

import euler.sum
import euler.sequence.primes

fun main(args : Array<String>) {
  val result = primes().takeWhile { it < 2000000 }.toList()
  println("the sum of all the ${result.size()} primes below two million is ${result.sum()}")
}
