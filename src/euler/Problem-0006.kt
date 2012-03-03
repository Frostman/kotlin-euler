package euler.problem0006

import euler.sum
import std.util.fold

fun main(args : Array<String>) {
  val start = 1; val end = 100; val range = start..end
  val squareSum = range.sum() * range.sum()
  val sumSquares = range.toList().fold(0) { (a: Int, b: Int) -> a + (b * b) }

  println("($start + .. + $end)^2 - ($start^2 + .. + $end^2) = $squareSum - $sumSquares = ${squareSum - sumSquares}")
}
