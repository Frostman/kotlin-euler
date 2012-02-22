package euler.problem0001

import java.util.Collection
import std.util.arrayList
import std.util.fold

fun main(args : Array<String>) {
  val result = sum(1..999 filter { n -> n multipleOf 3 || n multipleOf 5 })
  println("the sum of all the multiples of 3 or 5 below 1000 is $result")
}

inline fun sum(c: Collection<Int>) = c.fold(0) { (a: Int, b: Int) -> a + b }
inline fun IntRange.filter(p: (Int) -> Boolean) = filter(arrayList<Int>(), p)
inline fun Int.multipleOf(n: Int) = this % n == 0
