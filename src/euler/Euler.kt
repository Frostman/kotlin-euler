package euler

import java.math.BigInteger
import java.util.ArrayList
import java.util.LinkedList
import java.util.List

import kotlin.math.plus
import kotlin.math.times

// candidates for kotlin
inline fun Any.toCharList() = toString().iterator().toArrayList()
inline fun Any.toDigits() = toCharList().map { (c: Char) -> Character.getNumericValue(c) }
inline fun Any.isPalindrome() = toCharList() == toCharList().reverse()

// candidates for kotlin.math
inline fun <T: Number> bigInt(n: T) = BigInteger(n.toString())

inline fun factorial(n: Int, product: BigInteger = bigInt(1)): BigInteger = if (n == 0) product else factorial(n - 1, n * product)
inline fun Int.times(multiplicand: BigInteger) = bigInt(this) * multiplicand

inline fun Int.multipleOf(n: Int) = toLong() multipleOf n
inline fun Long.multipleOf(n: Int) = this % n == 0.toLong()
inline fun BigInteger.multipleOf(n: Int) = this % bigInt(n) == bigInt(0)

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

// candidates for JavaIterables.kt

// sum
inline fun java.lang.Iterable<Int>.sum() = fold(0) { (a, b) -> a + b }
inline fun java.lang.Iterable<Float>.sum() = fold(0.toFloat()) { (a, b) -> a + b }
inline fun java.lang.Iterable<Double>.sum() = fold(0.toDouble()) { (a, b) -> a + b }
inline fun java.lang.Iterable<Long>.sum() = fold(0.toLong()) { (a, b) -> a + b }
inline fun java.lang.Iterable<BigInteger>.sum() = fold(bigInt(0)) { (a, b) -> a + b }

inline fun java.util.Iterator<Int>.sum() = fold(0) { (a, b) -> a + b }
inline fun java.util.Iterator<Float>.sum() = fold(0.toFloat()) { (a, b) -> a + b }
inline fun java.util.Iterator<Double>.sum() = fold(0.toDouble()) { (a, b) -> a + b }
inline fun java.util.Iterator<Long>.sum() = fold(0.toLong()) { (a, b) -> a + b }
inline fun java.util.Iterator<BigInteger>.sum() = fold(bigInt(0)) { (a, b) -> a + b }

inline fun Iterable<Int>.sum() = fold(0) { (a: Int, b: Int) -> a + b }
inline fun Iterable<Float>.sum() = fold(0.toFloat()) { (a: Float, b: Float) -> a + b }
inline fun Iterable<Double>.sum() = fold(0.toDouble()) { (a: Double, b: Double) -> a + b }
inline fun Iterable<Long>.sum() = fold(0.toLong()) { (a, b: Long) -> a + b }
inline fun Iterable<BigInteger>.sum() = fold(bigInt(0)) { (a: BigInteger, b: BigInteger) -> a + b }

// product
inline fun java.lang.Iterable<Int>.product() = fold(1) { (a, b) -> a * b }
inline fun java.lang.Iterable<Float>.product() = fold(1.toFloat()) { (a, b) -> a * b }
inline fun java.lang.Iterable<Double>.product() = fold(1.toDouble()) { (a, b) -> a * b }
inline fun java.lang.Iterable<Long>.product() = fold(1.toLong()) { (a, b) -> a * b }
inline fun java.lang.Iterable<BigInteger>.product() = fold(bigInt(1)) { (a, b) -> a * b }

inline fun java.util.Iterator<Int>.product() = fold(1) { (a, b) -> a * b }
inline fun java.util.Iterator<Float>.product() = fold(1.toFloat()) { (a, b) -> a * b }
inline fun java.util.Iterator<Double>.product() = fold(1.toDouble()) { (a, b) -> a * b }
inline fun java.util.Iterator<Long>.product() = fold(1.toLong()) { (a, b) -> a * b }
inline fun java.util.Iterator<BigInteger>.product() = fold(bigInt(1)) { (a, b) -> a * b }

inline fun Iterable<Int>.product() = fold(1) { (a: Int, b: Int) -> a * b }
inline fun Iterable<Float>.product() = fold(1.toFloat()) { (a: Float, b: Float) -> a * b }
inline fun Iterable<Double>.product() = fold(1.toDouble()) { (a: Double, b: Double) -> a * b }
inline fun Iterable<Long>.product() = fold(1.toLong()) { (a: Long, b: Long) -> a * b }
inline fun Iterable<BigInteger>.product() = fold(bigInt(1)) { (a: BigInteger, b: BigInteger) -> a * b }

// max
inline fun java.lang.Iterable<Int>.max() = fold(0) { (a, b) -> Math.max(a, b) }
inline fun java.lang.Iterable<Float>.max() = fold(0.toFloat()) { (a, b) -> Math.max(a, b) }
inline fun java.lang.Iterable<Double>.max() = fold(0.toDouble()) { (a, b) -> Math.max(a, b) }
inline fun java.lang.Iterable<Long>.max() = fold(0.toLong()) { (a, b) -> Math.max(a, b) }

inline fun java.util.Iterator<Int>.max() = fold(0) { (a, b) -> Math.max(a, b) }
inline fun java.util.Iterator<Float>.max() = fold(0.toFloat()) { (a, b) -> Math.max(a, b) }
inline fun java.util.Iterator<Double>.max() = fold(0.toDouble()) { (a, b) -> Math.max(a, b) }
inline fun java.util.Iterator<Long>.max() = fold(0.toLong()) { (a, b) -> Math.max(a, b) }

inline fun Iterable<Int>.max() = fold(0) { (a: Int, b: Int) -> Math.max(a, b) }
inline fun Iterable<Float>.max() = fold(0.toFloat()) { (a: Float, b: Float) -> Math.max(a, b) }
inline fun Iterable<Double>.max() = fold(0.toDouble()) { (a: Double, b: Double) -> Math.max(a, b) }
inline fun Iterable<Long>.max() = fold(0.toLong()) { (a: Long, b: Long) -> Math.max(a, b) }

inline fun <T: Any> Iterable<T>.findPair(predicate: (T, T) -> Boolean): #(T, T)? {
  for (a in this) for (b in this) if ((predicate)(a, b)) return #(a, b)
  return null
}

inline fun <T: Any> Iterable<T>.findTriplet(predicate: (T, T, T) -> Boolean): #(T, T, T)? {
  for (a in this) for (b in this) for (c in this) if ((predicate)(a, b, c)) return #(a, b, c)
  return null
}

// candidates for kotlin.util
inline fun <T: Any> List<T>.permutations(): List<List<T>> {
  return if (isEmpty()) arrayList(this) else {
    val result = ArrayList<List<T>>()
    for (head in this) for (permutation in (this - head).permutations()) result.add(head + permutation)
    return result
  }
}

inline fun <T: Any> List<T>.rotations(): List<List<T>> {
  val result = arrayList<List<T>>(this)
  val linkedList = LinkedList(this)
  for (count in 2..size()) {
    linkedList.addLast(linkedList.removeFirst())
    result.add(ArrayList(linkedList))
  }
  return result
}

inline fun <T: Any> T.plus(list: List<T>): List<T> {
  val copy = ArrayList(list)
  copy.add(0, this)
  return copy
}

inline fun <T: Any> List<T>.minus(element: T): List<T> {
  val copy = ArrayList(this)
  copy.remove(element)
  return copy
}
