package euler.sequence

import std.util.fold
import java.util.NoSuchElementException

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
