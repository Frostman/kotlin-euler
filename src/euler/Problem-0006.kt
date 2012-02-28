package euler.problem0006

import euler.sum
import std.util.fold

fun main(args : Array<String>) {
  val range = 1..100
  val sum = range.toList().sum()
  val squareSum = sum * sum
  val sumSquares = range.toList().fold(0) { (a: Int, b: Int) -> a + (b * b) }

  println("(${range.start} + .. + ${range.end})^2 - (${range.start}^2 + .. + ${range.end}^2) = $squareSum - $sumSquares = ${squareSum - sumSquares}")
}
