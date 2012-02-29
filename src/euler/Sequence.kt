package euler.sequence

import std.util.fold
import java.util.NoSuchElementException

inline fun String.grouped(size: Int, iterator: CharIterator = iterator()) = object : Sequence<String>() {
  fun group(): String? {
    if (iterator.hasNext) {
      val window = StringBuilder()
      for (i in 1..size) if (iterator.hasNext) window.append(iterator.next())
      return window.toString()
    }
    return null
  }
  override fun iterator(): Iterator<String> = YieldingIterator<String> { group() }
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

    override fun iterator(): Iterator<String> = YieldingIterator<String> { slide() }
}

inline fun <T> sequence(vararg elements: T): Sequence<T> = object : Sequence<T>() {
  val iterator: Iterator<T> = elements.iterator()

  fun next(): T? { return if (iterator.hasNext) iterator.next() else null }

  override fun iterator(): Iterator<T> = YieldingIterator<T> { next() }
}

abstract class Sequence<T> : Iterable<T> {

  fun filter(predicate: (T) -> Boolean): Sequence<T> {
    val iterator = iterator()

    return object : Sequence<T>() {
      fun filter(): T? {
        while (iterator.hasNext) {
          val item = iterator.next()
          if ((predicate)(item)) return item
        }
        return null
      }

      override fun iterator(): Iterator<T> = YieldingIterator<T> { filter() }
    }
  }

  fun takeWhile(predicate: (T) -> Boolean): Sequence<T> {
    val iterator = iterator()

    return object : Sequence<T>() {
      fun takeWhile(): T? {
        if (iterator.hasNext) {
          val item = iterator.next()
          if ((predicate)(item)) return item
        }
        return null
      }

      override fun iterator(): Iterator<T> = YieldingIterator<T> { takeWhile() }
    }
  }
}

class YieldingIterator<T>(val yield: () -> T?) : Iterator<T> {
  var current : T? = (yield)()

  override val hasNext: Boolean
  get() = current != null

  override fun next(): T {
    val next = current
    if (next != null) {
      current = (yield)()
      return next
    }
    else throw NoSuchElementException()
  }
}
