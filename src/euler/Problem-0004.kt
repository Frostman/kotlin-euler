package euler.problem0004

import std.util.reverse

fun main(args : Array<String>) {
  var max = 0; var multiplicand = 0; var multiplier = 0

  for (a in 100..999) {
    for (b in 100..999) {
      val product = a * b
      if (product.isPalindrome() && product > max) { // palindrome and maximum are orthogonal concerns to iterating
        max = product; multiplicand = a; multiplier = b
      }
    }
  }

  println("the largest palindrome made from the product of two 3-digit numbers is $multiplicand x $multiplier = $max")
}

inline fun Int.isPalindrome(): Boolean {
  val digits = toCharList()
  return digits == digits.reverse()
}

inline fun Any.toCharList() = toString().sure().toCharArray().iterator().toArrayList()
