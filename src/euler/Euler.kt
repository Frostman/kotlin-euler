package euler

import java.math.BigInteger
import java.util.List

import std.math.plus
import std.math.times
import std.util.fold

// candidates for std
inline fun Any.toCharList() = toString().iterator().toArrayList()

// candidates for std.math
inline fun factorial(n: Int, product: BigInteger = BigInteger("1")): BigInteger = if (n == 0) product else factorial(n - 1, n * product)
inline fun Int.times(multiplicand: BigInteger) = BigInteger(toString()) * multiplicand
inline fun Int.multipleOf(n: Int) = this % n == 0

inline fun BigInteger.multipleOf(n: Int) = this % BigInteger(n.toString()) == BigInteger("0")

inline fun java.lang.Iterable<Int>.sum() = fold(0) { (a: Int, b: Int) -> a + b }
inline fun java.lang.Iterable<Float>.sum() = fold(0.toFloat()) { (a: Float, b: Float) -> a + b }
inline fun java.lang.Iterable<Double>.sum() = fold(0.toDouble()) { (a: Double, b: Double) -> a + b }
inline fun java.lang.Iterable<Long>.sum() = fold(0.toLong()) { (a: Long, b: Long) -> a + b }
inline fun java.lang.Iterable<BigInteger>.sum() = fold(BigInteger("0")) { (a: BigInteger, b: BigInteger) -> a + b }

inline fun java.lang.Iterable<Int>.max() = fold(0) { (a: Int, b: Int) -> Math.max(a, b) }
inline fun java.lang.Iterable<Float>.max() = fold(0.toFloat()) { (a: Float, b: Float) -> Math.max(a, b) }
inline fun java.lang.Iterable<Double>.max() = fold(0.toDouble()) { (a: Double, b: Double) -> Math.max(a, b) }
inline fun java.lang.Iterable<Long>.max() = fold(0.toLong()) { (a: Long, b: Long) -> Math.max(a, b) }

// candidates for std.util
inline fun <T> T.plus(list: List<T>): List<T> {
  list.add(0, this)
  return list
}
