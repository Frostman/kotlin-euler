package euler.sequence

import euler.isPrime
import java.math.BigInteger

inline fun <T> sequence(vararg elements: T) = object : Sequence<T>() {
  val iterator: Iterator<T> = elements.iterator()

  fun next(): T? { return if (iterator.hasNext) iterator.next() else null }

  override fun iterator(): Iterator<T> = YieldingIterator { next() }
}

inline fun primes() = object : Sequence<Long>() {
  var number = 2.toLong()

  fun prime(): Long {
    while (!number.isPrime()) number++
    val result = number; number++
    return result
  }

  override fun iterator(): Iterator<Long> = YieldingIterator { prime() }
}

inline fun String.grouped(size: Int, iterator: CharIterator = iterator()) = object : Sequence<String>() {
  fun group(): String? {
    if (iterator.hasNext) {
      val window = StringBuilder()
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return null
  }
  override fun iterator(): Iterator<String> = YieldingIterator { group() }
}

inline fun String.sliding(size: Int, iterator: CharIterator = iterator()) = object : Sequence<String>() {
  val window = StringBuilder()

  fun slide(): String? {
    if (window.length() == 0) {
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return if (iterator.hasNext) window.deleteCharAt(0)?.append(iterator.next()).toString() else null
  }

  override fun iterator(): Iterator<String> = YieldingIterator { slide() }
}
