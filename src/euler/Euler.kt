package euler

import java.math.BigInteger
import java.util.ArrayList
import java.util.LinkedList
import java.util.List

import std.math.plus
import std.math.times

import std.util.arrayList
import std.util.fold
import std.util.groupBy
import std.util.map
import std.util.reverse

// candidates for std
inline fun Any.toCharList() = toString().iterator().toArrayList()
inline fun Any.toDigits() = toCharList().map { (c: Char) -> Character.getNumericValue(c) }
inline fun Any.isPalindrome() = toCharList() == toCharList().reverse()

// candidates for std.math
inline fun factorial(n: Int, product: BigInteger = BigInteger("1")): BigInteger = if (n == 0) product else factorial(n - 1, n * product)
inline fun Int.times(multiplicand: BigInteger) = BigInteger(toString()) * multiplicand

inline fun Int.multipleOf(n: Int) = this % n == 0
inline fun BigInteger.multipleOf(n: Int) = this % BigInteger(n.toString()) == BigInteger("0")

inline fun Int.isPrime() = toLong().isPrime()
inline fun Long.isPrime() = this > 1 && smallestPrimeFactor() == null

inline fun Int.numberOfDivisors(): Int = toLong().numberOfDivisors()
inline fun Long.numberOfDivisors(): Int {
  return primeFactors(this).groupBy { it }.values().map { (group: List<Long>) -> group.size() + 1 }.product()
}

inline fun primeFactors(n: Long): List<Long> {
  val primeFactor = n.smallestPrimeFactor()
  return if (primeFactor == null) arrayList(n) else primeFactor + primeFactors(n / primeFactor)
}

inline fun Int.smallestPrimeFactor() = toLong().smallestPrimeFactor()?.toInt()
inline fun Long.smallestPrimeFactor() = 2..Math.sqrt(toDouble()).toLong() find { this % it == 0.toLong() }

// add to JavaIterablesSpecial.kt
inline fun java.lang.Iterable<Int>.sum() = fold(0) { (a: Int, b: Int) -> a + b }
inline fun java.lang.Iterable<Float>.sum() = fold(0.toFloat()) { (a: Float, b: Float) -> a + b }
inline fun java.lang.Iterable<Double>.sum() = fold(0.toDouble()) { (a: Double, b: Double) -> a + b }
inline fun java.lang.Iterable<Long>.sum() = fold(0.toLong()) { (a: Long, b: Long) -> a + b }
inline fun java.lang.Iterable<BigInteger>.sum() = fold(BigInteger("0")) { (a: BigInteger, b: BigInteger) -> a + b }

inline fun java.lang.Iterable<Int>.product() = fold(1) { (a: Int, b: Int) -> a * b }
inline fun java.lang.Iterable<Float>.product() = fold(1.toFloat()) { (a: Float, b: Float) -> a * b }
inline fun java.lang.Iterable<Double>.product() = fold(1.toDouble()) { (a: Double, b: Double) -> a * b }
inline fun java.lang.Iterable<Long>.product() = fold(1.toLong()) { (a: Long, b: Long) -> a * b }
inline fun java.lang.Iterable<BigInteger>.product() = fold(BigInteger("1")) { (a: BigInteger, b: BigInteger) -> a * b }

inline fun java.lang.Iterable<Int>.max() = fold(0) { (a: Int, b: Int) -> Math.max(a, b) }
inline fun java.lang.Iterable<Float>.max() = fold(0.toFloat()) { (a: Float, b: Float) -> Math.max(a, b) }
inline fun java.lang.Iterable<Double>.max() = fold(0.toDouble()) { (a: Double, b: Double) -> Math.max(a, b) }
inline fun java.lang.Iterable<Long>.max() = fold(0.toLong()) { (a: Long, b: Long) -> Math.max(a, b) }

// add to JetIterables.kt
inline fun Iterable<Int>.sum() = fold(0) { (a: Int, b: Int) -> a + b }
inline fun Iterable<Float>.sum() = fold(0.toFloat()) { (a: Float, b: Float) -> a + b }
inline fun Iterable<Double>.sum() = fold(0.toDouble()) { (a: Double, b: Double) -> a + b }
inline fun Iterable<Long>.sum() = fold(0.toLong()) { (a: Long, b: Long) -> a + b }
inline fun Iterable<BigInteger>.sum() = fold(BigInteger("0")) { (a: BigInteger, b: BigInteger) -> a + b }

inline fun Iterable<Int>.max() = fold(0) { (a: Int, b: Int) -> Math.max(a, b) }
inline fun Iterable<Float>.max() = fold(0.toFloat()) { (a: Float, b: Float) -> Math.max(a, b) }
inline fun Iterable<Double>.max() = fold(0.toDouble()) { (a: Double, b: Double) -> Math.max(a, b) }
inline fun Iterable<Long>.max() = fold(0.toLong()) { (a: Long, b: Long) -> Math.max(a, b) }

inline fun <T> Iterable<T>.fold(seed: T, lambda: (a: T, b: T) -> T): T {
  var accumulator = seed; val iterator = iterator()
  while (iterator.hasNext) accumulator = (lambda)(accumulator, iterator.next())
  return accumulator
}

inline fun <T> Iterable<T>.findPair(predicate: (T, T) -> Boolean): #(T, T)? {
  for (a in this) for (b in this) if ((predicate)(a, b)) return #(a, b)
  return null
}

inline fun <T> Iterable<T>.findTriplet(predicate: (T, T, T) -> Boolean): #(T, T, T)? {
  for (a in this) for (b in this) for (c in this) if ((predicate)(a, b, c)) return #(a, b, c)
  return null
}

// candidates for std.util
inline fun <T> List<T>.permutations(): List<List<T>> {
  return if (isEmpty()) arrayList(this) else {
    val result = ArrayList<List<T>>()
    for (head in this) for (permutation in (this - head).permutations()) result.add(head + permutation)
    return result
  }
}

inline fun <T> List<T>.rotations(): List<List<T>> {
  val result = arrayList<List<T>>(this)
  val linkedList = LinkedList(this)
  for (count in 2..size()) {
    linkedList.addLast(linkedList.removeFirst())
    result.add(ArrayList(linkedList))
  }
  return result
}

inline fun <T> T.plus(list: List<T>): List<T> {
  val copy = ArrayList(list)
  copy.add(0, this)
  return copy
}

inline fun <T> List<T>.minus(element: T): List<T> {
  val copy = ArrayList(this)
  copy.remove(element)
  return copy
}
