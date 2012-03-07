package euler.problem0014

import euler.multipleOf
import euler.plus
import euler.sequence.Sequence
import euler.sequence.YieldingIterator

import java.util.ArrayList
import java.util.List
import std.util.arrayList

fun main(args : Array<String>) {
  val result = sequences().longest()
  println("the longest chain starts with ${result.get(0)}:\n$result")
}

inline fun sequences() = object : Sequence<List<Long>>() {
  val iterator: LongIterator = (1..1000000.toLong()).iterator()

  fun sequence(n: Long): List<Long> {
    if (n == 1.toLong()) return arrayList(n)
    return if (n multipleOf 2) n + sequence(n / 2) else n + sequence(3 * n + 1)
  }

  fun next(): List<Long>? = if (iterator.hasNext) sequence(iterator.next()) else null

  override fun iterator(): Iterator<List<Long>> = YieldingIterator { next() }
}

inline fun <T> Sequence<List<T>>.longest() = fold(ArrayList<T>) { (a: List<T>, b: List<T>) ->
  if (a.size() > b.size()) a else b
}
