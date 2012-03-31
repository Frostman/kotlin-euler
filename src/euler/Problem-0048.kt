package euler.problem0048

import euler.bigInt
import java.math.BigInteger
import kotlin.math.plus

fun main(args : Array<String>) {
  val limit = 1000
  val range = (1..limit).map { bigInt(it) }
  val series = range.fold(bigInt(0)) { (a: BigInteger, b: BigInteger) -> a + b.pow(b.intValue()).sure() }.toString().sure()
  val result = series.substring(series.length() - 10)

  println("the last ten digits of the series 1^1 + 2^2 + .. + $limit^$limit are $result from $series")
}
