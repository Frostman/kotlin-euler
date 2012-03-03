package euler.problem0016

import euler.sum
import euler.toDigits
import java.math.BigInteger

fun main(args : Array<String>) {
  val power = 1000
  val number = BigInteger(2).pow(power).sure()
  println("the sum of the digits of the number 2^$power is ${number.toDigits().sum()}")
}
