package euler.problem0004

import euler.isPalindrome
import euler.sequence.pairs
import euler.sequence.Sequence

fun main(args : Array<String>) {
  val result = (100..999).palindromes().max()
  // until labeled tuples are supported - see http://youtrack.jetbrains.com/issue/KT-1433
  val multiplier = result._1; val multiplicand = result._2; val product = result._3

  println("the largest palindrome made from the product of two 3-digit numbers is $multiplier x $multiplicand = $product")
}

inline fun Iterable<Int>.palindromes() = pairs() map { #(it._1, it._2, it._1 * it._2) } filter { it._3.isPalindrome() }

inline fun Sequence<#(Int, Int, Int)>.max() = fold(#(0, 0, 0)) { (a: #(Int, Int, Int), b: #(Int, Int, Int)) ->
  if (Math.max(a._3, b._3) == a._3) a else b
}
