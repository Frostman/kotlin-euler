package euler.problem0021

import euler.sum

fun main(args : Array<String>) {
  val limit = 10000
  val amicableNumbers = 1..limit filter { it.hasAmicablePair() }
  println("the sum of all the amicable numbers under $limit is sum$amicableNumbers = ${amicableNumbers.sum()}")
}

inline fun Int.hasAmicablePair(): Boolean {
  val candidate = properDivisors().sum()
  return this != candidate && this == candidate.properDivisors().sum()
}

inline fun Int.properDivisors() = 1 upto this - 1 filter { this % it == 0 }
