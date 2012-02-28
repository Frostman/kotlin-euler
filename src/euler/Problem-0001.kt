package euler.problem0001

import euler.multipleOf
import euler.sum

import java.util.Collection
import std.util.arrayList
import std.util.fold

fun main(args : Array<String>) {
  val result = (1..999).filter { n -> n multipleOf 3 || n multipleOf 5 }.sum()
  println("the sum of all the multiples of 3 or 5 below 1000 is $result")
}
