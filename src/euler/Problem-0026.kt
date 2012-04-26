package euler.problem0026

import euler.bigInt
import euler.iterators.primes

fun main(args : Array<String>) {
  val limit = 1000

  // see http://en.wikipedia.org/wiki/Repeating_decimal#Fractions_with_prime_denominators
  val result = primes().takeWhile { it < limit }.map { (d : Long) ->
    val period = 1..limit find { bigInt(10).modPow(bigInt(it), bigInt(d)) == bigInt(1) }
    if (period != null) #(d, period.sure()) else #(d, 1)
  }.max()
  // average execution time of 48.5923 milliseconds over 10 iterations

  println("the value of d < $limit for which 1/d contains the longest recurring cycle in its decimal fraction part is ${result._1} which has a period of ${result._2}")
}

inline fun java.util.Iterator<#(Long, Int)>.max() = fold(#(0.toLong(), 0)) { (a, b) -> if (a._2 > b._2) a else b }
