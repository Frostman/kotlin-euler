package euler.problem0016

import euler.bigInt
import euler.sum
import euler.toDigits

fun main(args : Array<String>) {
  val power = 1000

  // average execution time of 0.0165 milliseconds over 10 iterations
  val number = bigInt(2).pow(power).sure()

  println("the sum of the digits of the number 2^$power is ${number.toDigits().sum()}")
}
