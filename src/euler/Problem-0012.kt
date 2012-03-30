package euler.problem0012

import euler.numberOfDivisors
import euler.iterators.triangles

fun main(args : Array<String>) {
  val threshold = 500
  val result = triangles().find { (triangle: #(Int, Int)) -> triangle._2.numberOfDivisors() > threshold }.sure()
  println("the first triangle number to have over $threshold divisors is triangle #${result._1} = ${result._2}")
}
