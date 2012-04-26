package euler.problem0003

import euler.max
import euler.primeFactors

fun main(args : Array<String>) {
  val n = 600851475143

  // average execution time of 0.098 milliseconds over 10 iterations
  val primeFactors = primeFactors(n)

  println("the largest prime factor of $n is max$primeFactors = ${primeFactors.max()}")
}
