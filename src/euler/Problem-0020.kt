package euler.problem0020

import euler.factorial
import euler.sum
import euler.toCharList

import java.math.BigInteger
import std.util.map

fun main(args : Array<String>) {
  val n = 100
  val result = factorial(n)
  println("the sum of the digits in $n! = $result is ${result.sumOfDigits()}")
}

inline fun BigInteger.sumOfDigits() = toCharList().map { (c: Char) -> Character.getNumericValue(c) }.sum()
