package euler.problem0011

import java.io.File
import java.util.ArrayList
import java.util.List

fun main(args : Array<String>) {
    val numbers = ArrayList<List<Int>>()

    File("Problem-0011.txt").forEachLine("UTF-8") {
        numbers.add(it.split(" ").map {it.toInt()})
    }

    var max = 0
    for (line in 0..19) {
        for (column in 0..19) {
            if (line < 17 && column < 17) {
                max = Math.max(max, product(numbers, line, column) {#(it._1 + 1, it._2 + 1)})
            }
            if (line > 2 && column < 17) {
                max = Math.max(max, product(numbers, line, column) {#(it._1 - 1, it._2 + 1)})
            }
            if (line < 17) {
                max = Math.max(max, product(numbers, line, column) {#(it._1 + 1, it._2)})
            }
            if (column < 17) {
                max = Math.max(max, product(numbers, line, column) {#(it._1, it._2 + 1)})
            }
        }
    }

    println(max)
}

fun product(val numbers : List<List<Int>>, val line : Int, val column : Int, val newCoords : (#(Int, Int)) -> #(Int, Int)) : Int {
    var result = numbers[line][column]
    var pos = #(line, column)
    for (iter in 1..3) {
        pos = newCoords(pos)
        result *= numbers[pos._1][pos._2]
    }

    return result
}
