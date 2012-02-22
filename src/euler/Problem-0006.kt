package euler.problem0006

import std.util.fold

fun main(args : Array<String>) {
  val range = 1..100
  val sum = range.sum()
  val squareSum = sum * sum
  val sumSquares = range.sumSquares()

  println("(${range.start} + .. + ${range.end})^2 - (${range.start}^2 + .. + ${range.end}^2) = $squareSum - $sumSquares = ${squareSum - sumSquares}")
}

inline fun IntRange.sum() = toList().fold(0) { (a: Int, b: Int) -> a + b }
inline fun IntRange.sumSquares() = toList().fold(0) { (a: Int, b: Int) -> a + (b * b) }
