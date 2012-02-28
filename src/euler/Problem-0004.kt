package euler.problem0004

import euler.toCharList
import std.util.reverse

fun main(args : Array<String>) {
  var max = 0; var multiplier = 0; var multiplicand = 0

  for (a in 100..999) {
    for (b in 100..999) {
      val product = a * b
      if (product.isPalindrome() && product > max) { // palindrome and maximum are orthogonal concerns to iterating
        max = product; multiplier = a; multiplicand = b
      }
    }
  }

  println("the largest palindrome made from the product of two 3-digit numbers is $multiplier x $multiplicand = $max")
}

inline fun Int.isPalindrome() = toCharList() == toCharList().reverse()
