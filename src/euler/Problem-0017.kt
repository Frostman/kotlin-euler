package euler.problem0017

fun main(args : Array<String>) {
    var result = 0
    1..1000 forEach {
        result += say(it).letters()
    }
    println(result)
}

fun say (val number : Int) : String {
    return when (number) {
        1 -> "one"
        2 -> "two"
        3 -> "three"
        4 -> "four"
        5 -> "five"
        6 -> "six"
        7 -> "seven"
        8 -> "eight"
        9 -> "nine"
        10 -> "ten"
        11 -> "eleven"
        12 -> "twelve"
        13 -> "thirteen"
        14 -> "fourteen"
        15 -> "fifteen"
        18 -> "eighteen"
        in arrayList(16, 17, 19) -> say(number % 10) + "teen"
        20 -> "twenty"
        30 -> "thirty"
        40 -> "forty"
        50 -> "fifty"
        80 -> "eighty"
        in arrayList(60, 70, 90) -> say(number / 10) + "ty"
        in 21..99 -> say(number / 10 * 10) + "-" + say(number % 10)
        in 100..999 -> say(number / 100) + " hundred" + if (number % 100 == 0) "" else " and " + say(number % 100)
        1000 -> "one thousand"
        else -> throw IllegalArgumentException()
    }
}

fun String.letters() = this.toCharList().filterNot{it == ' ' || it == '-'}.size
