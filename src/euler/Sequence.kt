package euler.sequence

class Sequence<T>(val next: () -> T?) : Iterable<T> {

  override fun iterator(): Iterator<T> = YieldingIterator { (next)() }

  fun filter(predicate: (T) -> Boolean): Sequence<T> {
    val iterator = iterator()

    fun next(): T? {
      while (iterator.hasNext) {
        val item = iterator.next()
        if ((predicate)(item)) return item
      }
      return null
    }

    return Sequence<T> { next() }
  }

  fun fold(accumulator: T, transform: (T, T) -> T): T {
    var result = accumulator
    for (item in this) result = (transform)(result, item)
    return result
  }

  fun <R> map(transform: (T) -> R): Sequence<R> {
    val iterator = iterator()
    fun next(): R? = if (iterator.hasNext) (transform)(iterator.next()) else null
    return Sequence<R> { next() }
  }

  fun take(n: Int): Sequence<T> {
    fun countTo(n: Int): (T) -> Boolean {
      var count = 0
      return { ++count; count <= n }
    }
    return takeWhile(countTo(n))
  }

  fun takeWhile(predicate: (T) -> Boolean): Sequence<T> {
    val iterator = iterator()

    fun next(): T? {
      if (iterator.hasNext) {
        val item = iterator.next()
        if ((predicate)(item)) return item
      }
      return null
    }

    return Sequence<T> { next() }
  }
}

private class YieldingIterator<T>(val yield: () -> T?) : Iterator<T> {
  var current : T? = (yield)()

  override val hasNext: Boolean
  get() = current != null

  override fun next(): T {
    val next = current
    if (next != null) {
      current = (yield)()
      return next
    }
    throw java.util.NoSuchElementException()
  }
}
