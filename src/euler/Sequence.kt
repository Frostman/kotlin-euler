package euler.sequence

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

      override fun iterator(): Iterator<T> = YieldingIterator { filter() }
    }
  }

  fun take(n: Int): Sequence<T> {
    fun countTo(n: Int): (Any) -> Boolean {
      var count = 0
      return { ++count; count <= n }
    }
    return takeWhile(countTo(n))
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

      override fun iterator(): Iterator<T> = YieldingIterator { takeWhile() }
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
    else throw java.util.NoSuchElementException()
  }
}
