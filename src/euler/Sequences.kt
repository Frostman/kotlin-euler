package euler.sequence

import euler.isPrime
import java.math.BigInteger
import kotlin.math.plus

inline fun <T> sequence(vararg elements: T): Sequence<T> {
  val iterator: Iterator<T> = elements.iterator()
  return Sequence<T> { if (iterator.hasNext) iterator.next() else null }
}

inline fun primes(): Sequence<Long> {
  var number = 2.toLong()

  fun nextPrime(): Long {
    while (!number.isPrime()) number++
    val result = number; number++
    return result
  }

  return Sequence<Long> { nextPrime() }
}

inline fun fibonacci(): Sequence<BigInteger> {
  val iterator: Iterator<#(Int, BigInteger)> = fibonacciWithIndices().iterator()
  return Sequence<BigInteger> { iterator.next()._2 }
}

inline fun fibonacciWithIndices(): Sequence<#(Int, BigInteger)> {
  var a = #(0, BigInteger("0")); var b = #(1, BigInteger("1"))

  fun nextFibonacci(): #(Int, BigInteger) {
    val result = #(b._1 + 1, a._2 + b._2); a = b; b = result
    return result
  }

  return Sequence<#(Int, BigInteger)> { nextFibonacci() }
}

inline fun triangles(): Sequence<#(Int, Int)> {
  var n = 0; var sum = 0

  fun nextTriangle(): #(Int, Int) {
    sum += ++n
    return #(n, sum)
  }

  return Sequence<#(Int, Int)> { nextTriangle() }
}

inline fun <T> Iterable<T>.pairs(range: Iterable<T> = this): Sequence<#(T, T)> {
  val first = range.iterator(); var second = range.iterator(); var a: T? = null

  fun nextPair(): #(T, T)? {
    if (a == null && first.hasNext) a = first.next()
    if (second.hasNext) return #(a.sure(), second.next())
    if (first.hasNext) {
      a = first.next(); second = range.iterator()
      return #(a.sure(), second.next())
    }
    return null
  }

  return Sequence<#(T, T)> { nextPair() }
}

inline fun String.grouped(size: Int, iterator: CharIterator = iterator()): Sequence<String> {
  fun nextGroup(): String? {
    if (iterator.hasNext) {
      val window = StringBuilder()
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return null
  }

  return Sequence<String> { nextGroup() }
}

inline fun String.sliding(size: Int, iterator: CharIterator = iterator()): Sequence<String> {
  val window = StringBuilder()

  fun nextWindow(): String? {
    if (window.length() == 0) {
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return if (iterator.hasNext) window.deleteCharAt(0)?.append(iterator.next()).toString() else null
  }

  return Sequence<String> { nextWindow() }
}
