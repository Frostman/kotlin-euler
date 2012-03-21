package euler.sequence

import euler.isPrime
import java.math.BigInteger
import kotlin.math.plus
import kotlin.sequences.Sequence

inline fun primes(): Sequence<Long> {
  var number = 2.toLong()

  fun nextPrime(): Long {
    while (!number.isPrime()) number++
    val result = number; number++
    return result
  }

  return Sequence<Long> { nextPrime() }
}

inline fun fibonacci(): Sequence<BigInteger> {
  val iterator: Iterator<#(Int, BigInteger)> = fibonacciWithIndices().iterator()
  return Sequence<BigInteger> { iterator.next()._2 }
}

inline fun fibonacciWithIndices(): Sequence<#(Int, BigInteger)> {
  var a = #(0, BigInteger("0")); var b = #(1, BigInteger("1"))

  fun nextFibonacci(): #(Int, BigInteger) {
    val result = #(b._1 + 1, a._2 + b._2); a = b; b = result
    return result
  }

  return Sequence<#(Int, BigInteger)> { nextFibonacci() }
}

inline fun triangles(): Sequence<#(Int, Int)> {
  var n = 0; var sum = 0

  fun nextTriangle(): #(Int, Int) {
    sum += ++n
    return #(n, sum)
  }

  return Sequence<#(Int, Int)> { nextTriangle() }
}
