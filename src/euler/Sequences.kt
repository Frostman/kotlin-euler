package euler.sequence

import euler.isPrime

inline fun <T> sequence(vararg elements: T) = object : Sequence<T>() {
  val iterator: Iterator<T> = elements.iterator()

  fun nextElement(): T? { return if (iterator.hasNext) iterator.next() else null }

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
