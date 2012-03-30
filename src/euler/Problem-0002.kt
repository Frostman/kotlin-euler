package euler.problem0002

import euler.multipleOf
import euler.sum
import euler.iterators.fibonacci

import java.math.BigInteger

fun main(args : Array<String>) {
  val limit = BigInteger("4000000")
  val numbers = fibonacci().takeWhile { it < limit }.filter { it multipleOf 2 }.toList()
  println("the sum of even Fibonacci numbers less than $limit is sum$numbers = ${numbers.sum()}")
}
