package euler.problem0003

import java.util.Collection
import java.util.List
import std.util.arrayList
import std.util.fold

fun main(args : Array<String>) {
  val n = 600851475143
  val primeFactors = primeFactors(n)
  println("the largest prime factor of $n is max$primeFactors = ${max(primeFactors)}")
}

inline fun max(c: Collection<Long>) = c.fold(0.toLong()) { (a: Long, b: Long) -> Math.max(a, b) }

// primes -> factor: n % it == 0 -> max
inline fun primeFactors(n: Long): List<Long> {
  val smallestPrimeFactor = 2.toLong()..Math.sqrt(n.toDouble()).toLong() find { n % it == 0.toLong() }
  return if (smallestPrimeFactor == null) arrayList(n.toLong()) else smallestPrimeFactor + primeFactors(n / smallestPrimeFactor)
}

inline fun <T> T.plus(list: List<T>): List<T> {
  list.add(0, this)
  return list
}
