package euler.problem0020

import euler.factorial
import euler.sum
import euler.toDigits

fun main(args : Array<String>) {
  val n = 100
  val result = factorial(n)
  println("the sum of the digits in $n! = $result is ${result.toDigits().sum()}")
}
