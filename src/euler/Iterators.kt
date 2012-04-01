package euler.iterators

import euler.bigInt
import euler.isPrime

import java.math.BigInteger
import java.util.Iterator

import kotlin.math.plus

fun primes(): Iterator<Long> {
  var number = 2.toLong()

  fun nextPrime(): Long {
    while (!number.isPrime()) number++
    val result = number; number++
    return result
  }

  return iterate<Long> { nextPrime() }
}

fun fibonacci(): Iterator<BigInteger> {
  val iterator = fibonacciWithIndices().iterator()
  return iterate<BigInteger> { iterator.next()._2 }
}

fun fibonacciWithIndices(): Iterator<#(Int, BigInteger)> {
  var a = #(0, bigInt(0)); var b = #(1, bigInt(1))

  fun nextFibonacci(): #(Int, BigInteger) {
    val result = #(b._1 + 1, a._2 + b._2); a = b; b = result
    return result
  }

  return iterate<#(Int, BigInteger)> { nextFibonacci() }
}

fun triangles(): Iterator<#(Int, Int)> {
  var n = 0; var sum = 0

  fun nextTriangle(): #(Int, Int) {
    sum += ++n
    return #(n, sum)
  }

  return iterate<#(Int, Int)> { nextTriangle() }
}

/**
 * Produces the [cartesian product](http://en.wikipedia.org/wiki/Cartesian_product#n-ary_product) as a sequence of ordered pairs of elements lazily obtained
 * from two [[Iterable]] instances
 */
fun <T: Any> Iterable<T>.times(other: Iterable<T>): Iterator<#(T, T)> {
  val first = iterator(); var second = other.iterator(); var a: T? = null

  fun nextPair(): #(T, T)? {
    if (a == null && first.hasNext) a = first.next()
    if (second.hasNext) return #(a.sure(), second.next())
    if (first.hasNext) {
      a = first.next(); second = other.iterator()
      return #(a.sure(), second.next())
    }
    return null
  }

  return iterate<#(T, T)> { nextPair() }
}

/**
 * Partitions this string into groups of fixed size strings, except the last will be truncated if the elements do not divide evenly
 *
 * *size* the number of characters per group
 */
fun String.grouped(size: Int): Iterator<String> {
  val iterator = iterator()

  fun nextGroup(): String? {
    if (iterator.hasNext) {
      val window = StringBuilder()
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return null
  }

  return iterate<String> { nextGroup() }
}

/**
 * Groups elements in fixed size blocks by passing a *sliding window* over them, as opposed to partitioning them, as is done in [[grouped()]].
 * The last and the only element will be truncated if there are fewer characters than *size*
 *
 * *size* the number of characters per group
 */
fun String.sliding(size: Int): Iterator<String> {
  val iterator = iterator()
  val window = StringBuilder()

  fun nextWindow(): String? {
    if (window.length() == 0) {
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return if (iterator.hasNext) window.deleteCharAt(0)?.append(iterator.next()).toString() else null
  }

  return iterate<String> { nextWindow() }
}
