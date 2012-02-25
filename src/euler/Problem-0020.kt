package euler.problem0020

import java.math.BigInteger
import java.util.Collection
import std.math.times
import std.util.fold
import std.util.map

fun main(args : Array<String>) {
  val n = 100
  val result = factorial(n)
  println("the sum of the digits in $n! = $result is ${result.sumOfDigits()}")
}

inline fun factorial(n: Int, product: BigInteger = BigInteger("1")): BigInteger = if (n == 0) product else factorial(n - 1, n * product)

inline fun sum(c: Collection<Int>) = c.fold(0) { (a: Int, b: Int) -> a + b }
inline fun Int.times(n: BigInteger) = BigInteger(toString()) * n
inline fun BigInteger.sumOfDigits() = sum(toCharList().map { (c: Char) -> Character.getNumericValue(c) })
inline fun Any.toCharList() = toString().sure().toCharArray().iterator().toArrayList()
