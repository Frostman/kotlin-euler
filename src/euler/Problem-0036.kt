package euler.problem0036

import euler.isPalindrome
import euler.sum

fun main(args : Array<String>) {
  val limit = 1000000
  val result = (1..limit).filter { it.isPalindrome() && Integer.toBinaryString(it).sure().isPalindrome() }
  println("the sum of all numbers, less than $limit, which are palindromic in base 10 and base 2 is sum$result = ${result.sum()}")
}
