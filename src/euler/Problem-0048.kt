package euler.problem0048

import java.math.BigInteger
import kotlin.math.plus
import kotlin.util.fold

fun main(args : Array<String>) {
  val limit = 1000
  val range = (1..limit).map { (n: Int) -> BigInteger(n.toString()) }
  val series = range.fold(BigInteger("0")) { (a: BigInteger, b: BigInteger) -> a + b.pow(b.intValue()).sure() }.toString().sure()
  val result = series.substring(series.length() - 10)

  println("the last ten digits of the series 1^1 + 2^2 + .. + $limit^$limit are $result from $series")
}
