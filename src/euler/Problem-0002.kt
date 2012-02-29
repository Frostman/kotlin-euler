package euler.problem0002

import euler.multipleOf
import euler.sum
import euler.sequence.Sequence
import euler.sequence.YieldingIterator

import java.math.BigInteger
import std.math.plus

fun main(args : Array<String>) {
  val limit = BigInteger("4000000")
  val numbers = fibonacci().takeWhile { it < limit }.filter { it multipleOf 2 }.toList()
  println("the sum of even Fibonacci numbers less than $limit, that is sum${numbers} = ${numbers.sum()}")
}

inline fun fibonacci() = object : Sequence<BigInteger>() {
  var a = BigInteger("0"); var b = BigInteger("1")

  fun nextFibonacci(): BigInteger {
    val result = a + b; a = b; b = result
    return result
  }

  override fun iterator(): Iterator<BigInteger> = YieldingIterator { nextFibonacci() }
}
