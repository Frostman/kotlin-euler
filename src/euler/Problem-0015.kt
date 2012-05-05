package euler.problem0015

import java.util.HashMap

fun main(args : Array<String>) {
    println(routes(#(20, 20)))
}

fun routes(val size : #(Int, Int)) : Long {
    if (size._1 < 1 || size._2 < 1) {
        return 0.toLong()
    }

    if (routesCache.containsKey(size)) {
        return routesCache[size]!!
    }

    val stripeRoutes = stripeRoutes(size)
    if(stripeRoutes > 0) {
        return stripeRoutes
    }

    val result = routes(#(size._1 - 1, size._2)) + routes(#(size._1, size._2 - 1))
    routesCache.put(size, result)

    return result
}

/**
Returns 0 if it is not a stripe
*/
fun stripeRoutes(val size : #(Int, Int)) : Long {
    return if (size._1 == 1) size._2 + 1.toLong()
    else if (size._2 == 1) size._1 + 1.toLong()
    else 0.toLong()
}

val routesCache = HashMap<#(Int, Int), Long>(800)

