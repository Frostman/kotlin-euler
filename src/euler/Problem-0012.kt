package euler.problem0012

import euler.numberOfDivisors
import euler.iterators.triangles

fun main(args : Array<String>) {
  val threshold = 500

  // average execution time of 62.3622 milliseconds over 10 iterations
  val result = triangles().find { triangle -> triangle._2.numberOfDivisors() > threshold }.sure()

  println("the first triangle number to have over $threshold divisors is triangle #${result._1} = ${result._2}")
}
