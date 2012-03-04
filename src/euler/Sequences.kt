package euler.sequence

import euler.isPrime
import java.math.BigInteger
import std.math.plus

inline fun <T> sequence(vararg elements: T) = object : Sequence<T>() {
  val iterator: Iterator<T> = elements.iterator()

  fun nextElement(): T? = if (iterator.hasNext) iterator.next() else null

  override fun iterator(): Iterator<T> = YieldingIterator { nextElement() }
}

inline fun primes() = object : Sequence<Long>() {
  var number = 2.toLong()

  fun nextPrime(): Long {
    while (!number.isPrime()) number++
    val result = number; number++
    return result
  }

  override fun iterator(): Iterator<Long> = YieldingIterator { nextPrime() }
}

inline fun fibonacci() = object : Sequence<BigInteger>() {
  val iterator: Iterator<#(Int, BigInteger)> = fibonacciWithIndices().iterator()
  override fun iterator(): Iterator<BigInteger> = YieldingIterator { iterator.next()._2 }
}

inline fun fibonacciWithIndices() = object : Sequence<#(Int, BigInteger)>() {
  var a = #(0, BigInteger(0)); var b = #(1, BigInteger(1))

  fun nextFibonacci(): #(Int, BigInteger) {
    val result = #(b._1 + 1, a._2 + b._2); a = b; b = result
    return result
  }

  override fun iterator(): Iterator<#(Int, BigInteger)> = YieldingIterator { nextFibonacci() }
}

inline fun triangles() = object : Sequence<#(Int, Int)>() {
  var n = 0; var sum = 0
  fun nextTriangle(): #(Int, Int) {
    sum += ++n
    return #(n, sum)
  }
  override fun iterator(): Iterator<#(Int, Int)> = YieldingIterator { nextTriangle() }
}

inline fun <T> Iterable<T>.pairs(range: Iterable<T> = this) = object : Sequence<#(T, T)>() {
  val first = range.iterator(); var second = range.iterator(); var a: T? = null

  fun nextPair(): #(T, T)? {
    if (a == null && first.hasNext) a = first.next()
    if (second.hasNext) return #(a.sure(), second.next())
    if (first.hasNext) {
      a = null; second = range.iterator()
      return nextPair()
    }
    return null
  }

  override fun iterator(): Iterator<#(T, T)> = YieldingIterator { nextPair() }
}

inline fun String.grouped(size: Int, iterator: CharIterator = iterator()) = object : Sequence<String>() {
  fun nextGroup(): String? {
    if (iterator.hasNext) {
      val window = StringBuilder()
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return null
  }
  override fun iterator(): Iterator<String> = YieldingIterator { nextGroup() }
}

inline fun String.sliding(size: Int, iterator: CharIterator = iterator()) = object : Sequence<String>() {
  val window = StringBuilder()

  fun nextWindow(): String? {
    if (window.length() == 0) {
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return if (iterator.hasNext) window.deleteCharAt(0)?.append(iterator.next()).toString() else null
  }

  override fun iterator(): Iterator<String> = YieldingIterator { nextWindow() }
}
