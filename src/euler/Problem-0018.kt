package euler.problem0018

import java.util.List
import java.util.ArrayList
import java.io.File
import euler.max

fun main(args : Array<String>) {
    var prevLine : List<Int> = arrayList(0, 0)
    var currLine : List<Int> = arrayList(0)

    File("Problem-0018.txt").forEachLine("UTF-8") {
        val values : List<Int> = it.split(" ").map {it.toInt()}

        for (idx in values.indices) {
            currLine.add(values[idx] + Math.max(prevLine[idx], prevLine[idx + 1]))
        }

        currLine.add(0)
        prevLine = currLine
        currLine = arrayList(0)
    }

    println(prevLine.max())
}



