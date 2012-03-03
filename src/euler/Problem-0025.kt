package euler.problem0025

import euler.sequence.fibonacciWithIndices

fun main(args : Array<String>) {
  val size = 1000
  val result = fibonacciWithIndices().find { it._2.toString()?.length == size }.sure()
  println("the first term in the Fibonacci sequence to contain $size digits is term F(${result._1}) = ${result._2}")
}
