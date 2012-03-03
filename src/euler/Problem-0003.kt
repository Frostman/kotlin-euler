package euler.problem0003

import euler.max
import euler.plus
import euler.smallestPrimeFactor

import java.util.List
import std.util.arrayList

fun main(args : Array<String>) {
  val n = 600851475143
  val primeFactors = primeFactors(n)
  println("the largest prime factor of $n is max$primeFactors = ${primeFactors.max()}")
}

inline fun primeFactors(n: Long): List<Long> {
  val primeFactor = n.smallestPrimeFactor()
  return if (primeFactor == null) arrayList(n.toLong()) else primeFactor + primeFactors(n / primeFactor)
}
